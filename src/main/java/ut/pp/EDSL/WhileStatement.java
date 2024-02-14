package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class WhileStatement holds all information required to compile a while statement of the form
 * while([condition]) {[Statements]}
 */
public class WhileStatement implements Statement {
    private Expression condition;
    private List<Statement> statements;

    /**
     * WhileStatement constructor
     * @param condition     the condition expression
     * @param statements    list of statements inside the while body
     */
    public WhileStatement(Expression condition, List<Statement> statements) {
        this.condition = condition;
        this.statements = statements;
    }

    /**
     * Generate the spril instructions that can be run to execute the while loop
     * @return spril instruction list
     */
    @Override
    public List<Instruction> toSpril() {
        // Code layout:
        //- Check while condition
        //- Branch to after while if condition not met
        //- While body
        //- Jump to checking while condition
        List<Instruction> instructions = new ArrayList<>();

        // Pt. 1: Instructions for checking while condition
        condition.setTargetReg("regA");
        List<Instruction> checkExprInstructions = condition.toSpril();
        checkExprInstructions.get(0).addComment("While condition check");

        // Pt. 3: Instructions for while body
        List<Instruction> bodyInstructions = new ArrayList<>();
        for (Statement stmnt : statements) {
            bodyInstructions.addAll(stmnt.toSpril());
        }
        if (bodyInstructions.size() > 0) bodyInstructions.get(0).addComment("While body");

        // Pt. 4: Jump back to checking condition
        TargetPtr whileExprPtr = new TargetPtr(checkExprInstructions.get(0));
        Instruction jumpBackToExpr = new Instruction(OpCode.JUMP, whileExprPtr);
        // Pt. 5: Add nop instruction that can be branched to if the while condition is not met
        Instruction afterWhile = new Instruction(OpCode.NOP);

        // Pt. 2: Conditional branch after checking while condition
        List<Instruction> conditionalBranchInstructions = new ArrayList<>();
        //     Branch should be taken if expression is false, so we compute it's inverse
        //       regA <- !regA
        conditionalBranchInstructions.add(new Instruction(OpCode.COMPUTE, new Operator("Equal"), new RegAddr("regA"), new RegAddr("reg0"), new RegAddr("regA")));
        //      branch to past the while body if regA holds true
        TargetPtr afterWhilePtr = new TargetPtr(afterWhile);
        conditionalBranchInstructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regA"), afterWhilePtr));


        // Now create instruction list by putting the above parts in order
        instructions.addAll(checkExprInstructions);
        instructions.addAll(conditionalBranchInstructions);
        instructions.addAll(bodyInstructions);
        instructions.add(jumpBackToExpr);
        instructions.add(afterWhile);

        return instructions;
    }
}
