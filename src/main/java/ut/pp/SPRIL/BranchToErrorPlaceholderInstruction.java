package ut.pp.SPRIL;

import ut.pp.EDSL.Error;

import java.util.ArrayList;
import java.util.List;

/**
 * Placeholder put in place when a statement gives an error are turned into SPRIL code
 * This happens when an index outside the range of an array is accessed, or when dividing by 0
 * Program.toSpril later calls setJumpTargetPtr to provide the jumpTarget and generate the instructions
 * that are represented by this placeholder
 * These instructions will then replace the placeholder in Finalizer.replacePlaceholders
 */
public class BranchToErrorPlaceholderInstruction extends Instruction {
    private List<Instruction> instructions;
    private RegAddr branchCondition;
    private Error error;

    public BranchToErrorPlaceholderInstruction(String reg, Error error) {
        this.branchCondition = new RegAddr(reg);
        this.error = error;
    }

    /**
     * Generate instructions for jumping to the error handling section in the code
     * Overwrites contents of regF
     * @param jumpTarget    instruction line number where the code of the thread starts
     */
    public void setJumpTargetPtr(Instruction jumpTarget) {
        instructions = new ArrayList<>();
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(error.getId()), new RegAddr("regF")));
        instructions.add(new Instruction("Branch to error", OpCode.BRANCH, branchCondition, new TargetPtr(jumpTarget)));
    }

    /**
     * Get the instructions jumping to the error handling section
     * Requires setJumpTargetPtr to be called first
     * @return instructions for fork
     */
    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    /**
     * Returns the JumpToError instruction with the instruction it represents
     * For debugging purposes, when displaying code from the intermediate representation
     * @return JumpToError instruction as string
     */
    @Override
    public String toString() {
        if (instructions == null)
            return String.format("JumpToError");
        else
            return String.format("JumpToError: {%s}", instructions.get(0));
    }
}
