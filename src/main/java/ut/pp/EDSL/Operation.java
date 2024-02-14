package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Operation holds all necessary information to compile an operation on two expressions into spril instructions
 * Example operations are (5 < d), (true || false) and (-(6) * r)
 */
public class Operation extends Expression {
    private Expression e1, e2; // NOT and MINUS operations ignore e2
    private Operator operator;

    /**
     * Operation constructor
     * @param e1        Expression left of the operator, or the only expression in case of Not (!) and Minus (-)
     * @param e2        Expression right of the operator, or any value in case of Not and Minus
     * @param operator  Operator
     */
    public Operation(Expression e1, Expression e2, Operator operator) {
        this.e1 = e1;
        this.e2 = e2;
        this.operator = operator;
    }

    private List<Instruction> calculateDivisionInstructions() {
        List<Instruction> instructions = new ArrayList<>();

        // Branch to error when dividing by 0
        instructions.add(new Instruction("Compute division", OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Equal"), new RegAddr("regB"), new RegAddr("reg0"), new RegAddr("regD")));
        instructions.add(new BranchToErrorPlaceholderInstruction("regD", Error.DivByZero));

        // make sure both registers contain positive values, in case exactly one value was negative, push 1 to stack, otherwise push 0
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("GtE"), new RegAddr("regA"), new RegAddr("reg0"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("GtE"), new RegAddr("regB"), new RegAddr("reg0"), new RegAddr("regD")));
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(-1), new RegAddr("regE")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regC"), new Rel(2)));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Mul"), new RegAddr("regA"), new RegAddr("regE"), new RegAddr("regA")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regD"), new Rel(2)));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Mul"), new RegAddr("regB"), new RegAddr("regE"), new RegAddr("regB")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Xor"), new RegAddr("regC"), new RegAddr("regD"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.PUSH, new RegAddr("regC")));

        // regE holds the remainder
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(0), new RegAddr("regE")));
        // regF holds the bit we're currently looking at
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(1), new RegAddr("regF")));

        // While regF < regA, left shift
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(1), new RegAddr("regD")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("GtE"), new RegAddr("regF"), new RegAddr("regA"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regC"), new Rel(3)));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("LShift"), new RegAddr("regF"), new RegAddr("regD"), new RegAddr("regF")));
        instructions.add(new Instruction(OpCode.JUMP, new Rel(-3)));

        // regD holds the answer calculated so far (is already set to 0, because the equality check before failed)
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(0), new RegAddr("regD")));

        // Long division
        // end if regF == 0
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Equal"), new RegAddr("regF"), new RegAddr("reg0"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regC"), new Rel(14)));
        // Remainder = remainder * 2
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(1), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("LShift"), new RegAddr("regE"), new RegAddr("regC"), new RegAddr("regE")));
        // if the currently inspected bit == 1 in regA, we add 1 to the remainder
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("And"), new RegAddr("regF"), new RegAddr("regA"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Equal"), new RegAddr("regC"), new RegAddr("reg0"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regC"), new Rel(2)));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Incr"), new RegAddr("regE"), new RegAddr("regE"), new RegAddr("regE")));

        // if remainder >= divisor we subtract divisor from remainder and set the current inspected bit in the answer
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Lt"), new RegAddr("regE"), new RegAddr("regB"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regC"), new Rel(3)));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Sub"), new RegAddr("regE"), new RegAddr("regB"), new RegAddr("regE")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Or"), new RegAddr("regD"), new RegAddr("regF"), new RegAddr("regD")));

        // shift currently inspected bit
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(1), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("RShift"), new RegAddr("regF"), new RegAddr("regC"), new RegAddr("regF")));

        // Jump to start of loop
        instructions.add(new Instruction(OpCode.JUMP, new Rel(-14)));

        // If exactly one argument was negative, invert result
        instructions.add(new Instruction(OpCode.POP, new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Equal"), new RegAddr("regC"), new RegAddr("reg0"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regC"), new Rel(3)));
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(-1), new RegAddr("regE")));
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Mul"), new RegAddr("regE"), new RegAddr("regD"), new RegAddr("regD")));
        // Copy regD to targetReg
        instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Add"), new RegAddr("regD"), new RegAddr("reg0"), targetReg));

        return instructions;
    }

    /**
     * Generate spril code that can be ran to calculate the result of the operation and store it in targetReg,
     * given by superclass Expression
     * @return spril instruction list
     */
    @Override
    public List<Instruction> toSpril() {
        List<Instruction> instructions = new ArrayList<>();

        // if the operation is on arrays, we take special steps
        if (e1 instanceof LoadArrayValues || e1 instanceof LoadArrayVar) {
            return toSprilForArrays();
        }

        // First get the instructions to calculate right child and put result on stack
        boolean e2PushedToStack = false;
        if (operator != Operator.NOT && operator != Operator.NEG) {
            // If e1 is an operation, calculating it will overwrite the contents of regB, so we need to store regB on the stack
            if (e1 instanceof Operation || (e1 instanceof LoadVar && ((LoadVar)e1).isArrayVar())) {
                e2.setPushResultToStack(true);
                e2PushedToStack = true;
            }
            else {
                e2.setTargetReg("regB");
            }
            instructions.addAll(e2.toSpril());
        }
        // Then add instructions to calculate left child (this can overwrite the register that holds the result of the right child)
        e1.setTargetReg("regA");
        instructions.addAll(e1.toSpril());

        if (e2PushedToStack) {
            // Pop right child value from stack, store into regB
            instructions.add(new Instruction(OpCode.POP, new RegAddr("regB")));
        }



        // Generate code for the operation and store result in targetReg
        switch (operator) {
            case NOT:
                // targetReg <- regA == 0
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Equal"), new RegAddr("regA"), new RegAddr("reg0"), targetReg));
                break;
            case NEG:
                // targetReg <- 0 - regA
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Sub"), new RegAddr("reg0"), new RegAddr("regA"), targetReg));
                break;
            case STAR:
                // targetReg <- regA * regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Mul"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case DIV:
                // targetReg <- floor(regA / regB)
                instructions.addAll(calculateDivisionInstructions());
                break;
            case PLUS:
                // targetReg <- regA + regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Add"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case MINUS:
                // targetReg <- regA - regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Sub"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case EQUALS:
                // targetReg <- regA == regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Equal"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case SMALLER:
                // targetReg <- regA < regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Lt"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case SMALLEREQUALS:
                // targetReg <- regA <= regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("LtE"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case BIGGER:
                // targetReg <- regA > regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Gt"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case BIGGEREQUALS:
                // targetReg <- regA >= regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("GtE"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case NOTEQUAL:
                // targetReg <- regA != regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("NEq"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case AND:
                // targetReg <- regA && regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("And"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
            case OR:
                // targetReg <- regA || regB
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Or"), new RegAddr("regA"), new RegAddr("regB"), targetReg));
                break;
        }

        // Push result to stack
        if (pushResultToStack) {
            instructions.add(new Instruction(OpCode.PUSH, targetReg));
        }

        return instructions;
    }

    /**
     * If the operation is on arrays, it cannot be done by a single calculation instruction
     * the necessary instructions for the operation (which can be == or !=) is returned by this function
     * @return spril instruction list
     */
    private List<Instruction> toSprilForArrays() {
        List<Instruction> instructions = new ArrayList<>();

        // Get the expressions that load the individual variables in the arrays into a register
        List<? extends Expression> e1LoadExpressions = getIndividualLoadExpressions(e1);
        List<? extends Expression> e2LoadExpressions = getIndividualLoadExpressions(e2);

        // If the arrays are of different sizes, we can immediately return a result
        if (e1LoadExpressions.size() != e2LoadExpressions.size()) {
            if (operator == Operator.EQUALS)
                instructions.add(new Instruction(OpCode.LOAD, new ImmValue(0), targetReg));
            else
                instructions.add(new Instruction(OpCode.LOAD, new ImmValue(1), targetReg));
        }
        else {
            if (operator == Operator.EQUALS) {
                // regC holds the result so far: if all indices of the arrays had equal values up to this point, it is set to 1
                instructions.add(new Instruction(OpCode.LOAD, new ImmValue(1), new RegAddr("regC")));
                for (int i = 0; i < e1LoadExpressions.size(); i++) {
                    // Load value of elements into registers
                    Expression e1LoadExpr = e1LoadExpressions.get(i);
                    Expression e2LoadExpr = e2LoadExpressions.get(i);

                    // Compare the elements
                    Operation compOp = new Operation(e1LoadExpr, e2LoadExpr, Operator.EQUALS);
                    compOp.setTargetReg("regA");
                    instructions.addAll(compOp.toSpril());

                    // regC = regC && regA
                    instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("And"),
                            new RegAddr("regC"), new RegAddr("regA"), new RegAddr("regC")));
                }
            }
            else { // operator == Operator.NOTEQUAL
                // regC holds the result so far: if an index of the arrays had unequal values up to this point, it is set to 1
                instructions.add(new Instruction(OpCode.LOAD, new ImmValue(0), new RegAddr("regC")));
                for (int i = 0; i < e1LoadExpressions.size(); i++) {
                    // Load value of elements into registers
                    Expression e1LoadExpr = e1LoadExpressions.get(i);
                    Expression e2LoadExpr = e2LoadExpressions.get(i);

                    // Compare the elements
                    Operation compOp = new Operation(e1LoadExpr, e2LoadExpr, Operator.NOTEQUAL);
                    compOp.setTargetReg("regA");
                    instructions.addAll(compOp.toSpril());

                    // regC = regC || regA
                    instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Or"),
                            new RegAddr("regC"), new RegAddr("regA"), new RegAddr("regC")));
                }
            }


            // copy regC to targetReg
            instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("And"),
                    new RegAddr("regC"), new RegAddr("regC"), targetReg));
        }

        // Push result to stack
        if (pushResultToStack) {
            instructions.add(new Instruction(OpCode.PUSH, targetReg));
        }

        return instructions;
    }

    private List<? extends Expression> getIndividualLoadExpressions(Expression expr) {
        if (expr instanceof LoadArrayValues) {
            return ((LoadArrayValues) expr).getLoadExpressions();
        }
        else { //expr instanceof LoadArrayVar
            return ((LoadArrayVar) expr).getLoadExpressions();
        }
    }

    public enum Operator {
        NOT, NEG,
        STAR, DIV, PLUS, MINUS,
        EQUALS, SMALLER, SMALLEREQUALS, BIGGER, BIGGEREQUALS, NOTEQUAL,
        AND, OR;
    }
}
