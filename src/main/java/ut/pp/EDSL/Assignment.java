package ut.pp.EDSL;

import ut.pp.SPRIL.DirAddr;
import ut.pp.SPRIL.Instruction;
import ut.pp.SPRIL.OpCode;
import ut.pp.SPRIL.RegAddr;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Assignment holds all necessary information for compiling assignment statements
 * Assignments have the form [Variable] = [Expression] (e.g. x = 5+2)
 */
public class Assignment implements Statement {
    private Variable var;
    private Expression expr;

    /**
     * Assignment constructor
     * @param var   The variable on the left-hand side of the assignment
     * @param expr  The expression on the right-hand side of the assignment
     */
    public Assignment(Variable var, Expression expr) {
        this.var = var;
        this.expr = expr;
    }

    /**
     * Generate spril code that executes the assignment
     * @return List of spril instructions
     */
    @Override
    public List<Instruction> toSpril() {
        if (var instanceof SingleVar) {
            // Calculate value of the expression
            expr.setTargetReg("regA");
            List<Instruction> instructions = new ArrayList<>(expr.toSpril());
            // Store value in memory
            if (var.isShared()) {
                instructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("regA"), new DirAddr(var.getAddr())));
            }
            else {
                instructions.add(new Instruction(OpCode.STORE, new RegAddr("regA"), new DirAddr(var.getAddr())));
            }
            instructions.get(0).addComment("Assignment to " + var.getId());
            return instructions;
        }
        else { // var instanceOf ArrayVar
            List<Instruction> instructions = new ArrayList<>();

            int arrayBaseAddress = var.getAddr();

            // Get all the LoadConst or LoadVar expressions for a LoadArrayValues or LoadArrayVar expression respectively
            List<? extends Expression> individualLoadExpressions;
            if (expr instanceof LoadArrayValues) {
                individualLoadExpressions = ((LoadArrayValues) expr).getLoadExpressions();
            }
            else { //expr instanceof LoadArrayVar
                individualLoadExpressions = ((LoadArrayVar) expr).getLoadExpressions();
            }

            for (int addressOffset = 0; addressOffset < ((ArrayVar) var).getLength(); addressOffset++) {
                int arrayValAddress = arrayBaseAddress + addressOffset;
                Expression loadArrayExpr = individualLoadExpressions.get(addressOffset);

                // Store array value into register
                loadArrayExpr.setTargetReg("regA");
                instructions.addAll(loadArrayExpr.toSpril());

                // Store register content into memory
                if (var.isShared()) {
                    instructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("regA"), new DirAddr(arrayValAddress)));
                }
                else {
                    instructions.add(new Instruction(OpCode.STORE, new RegAddr("regA"), new DirAddr(arrayValAddress)));
                }
            }

            instructions.get(0).addComment("Assignment of array to " + var.getId());
            return instructions;
        }

    }
}