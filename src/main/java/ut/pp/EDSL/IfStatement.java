package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class IfStatement holds all necessary information to compile an if statement
 * IfStatements are of the form                 if ([expression]) {[Statements]}
 * possibly followed (a number of times)        elseif ([expression]) {[Statements]}
 * and possibly closed with                     else {[Statements]}
 */
public class IfStatement implements Statement {
    // Each condition corresponds to a set of statements that are run when the condition is met
    // If none of the conditions are met, and the statementList is 1 entry longer than the conditions, then
    // the final entry in statementLists corresponds to the else-clause en will be run
    private List<Expression> conditions;
    private List<List<Statement>> statementLists;

    /**
     * IfStatement constructor
     * @param conditions        The expressions in each of the if / else if clauses
     * @param statementLists    The Lists of statements in each of the if / else if / else bodies
     */
    public IfStatement(List<Expression> conditions, List<List<Statement>> statementLists) {
        this.conditions = conditions;
        this.statementLists = statementLists;
    }

    /**
     * Generate a list of spril instructions that can be run to execute the if statement
     * @return List of spril instructions
     */
    @Override
    public List<Instruction> toSpril() {
        //Code layout:
        //- Check condition 1
        //- Branch to next condition
        //- If statement 1 body
        //- Jump to end
        //- (Check condition 2
        //- Branch
        //- Statement 2 body
        //- Jump to end)
        //- (Else body)
        List<Instruction> instructions = new ArrayList<>();

        // First generate (but don't yet add) the spril code for all conditions
        List<List<Instruction>> instructionsForEachCondition = new ArrayList<>();
        for (Expression expr: conditions) {
            expr.setTargetReg("regA");
            List<Instruction> instr = new ArrayList<>(expr.toSpril());
            instructionsForEachCondition.add(instr);
            instr.get(0).addComment("Check if condition");
        }
        // Generate the else clause body that can be branched to if the final condition is false
        List<Instruction> elseBody = new ArrayList<>();
        if (statementLists.size() > conditions.size()) {
            // If there's an else clause, this will form the body of the else
            for (Statement stmnt : statementLists.get(statementLists.size()-1)) {
                elseBody.addAll(stmnt.toSpril());
                elseBody.get(0).addComment("Else body");
            }
        }
        Instruction afterElseBody = new Instruction(OpCode.NOP);

        // For each condition (so not for the else clause)
        for (int i = 0; i < conditions.size(); i++) {
            // Add the condition check instructions
            instructions.addAll(instructionsForEachCondition.get(i));
            // invert the result of the expression, so branch is taken if value is false
            instructions.add(new Instruction(OpCode.COMPUTE, new Operator("Equal"), new RegAddr("regA"), new RegAddr("reg0"), new RegAddr("regA")));

            // now branch to the next condition check if regA is true (so when the original condition was false)
            TargetPtr target;
            if (i < conditions.size()-1) {
                // branch to the first line of the next condition
                target = new TargetPtr(instructionsForEachCondition.get(i+1).get(0));
            }
            else {
                // if no more conditions exist, branch to the else body
                if (elseBody.size() > 0) {
                    target = new TargetPtr(elseBody.get(0));
                }
                else {
                    target = new TargetPtr(afterElseBody);
                }
            }
            instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regA"), target));

            // Add the if statement body after the conditional branch
            List<Instruction> ifBody = new ArrayList<>();
            List<Statement> statements = statementLists.get(i);
            for (Statement stmnt : statements) {
                ifBody.addAll(stmnt.toSpril());
            }
            if (ifBody.size() > 0) ifBody.get(0).addComment("If body");
            instructions.addAll(ifBody);
            // Jump to past the remaining code for all other elseif / else clauses
            if (i < statementLists.size()-1) {
                target = new TargetPtr(afterElseBody);
                instructions.add(new Instruction(OpCode.JUMP, target));
            }
        }

        // Add instructions for else
        instructions.addAll(elseBody);
        instructions.add(afterElseBody);

        return instructions;
    }
}
