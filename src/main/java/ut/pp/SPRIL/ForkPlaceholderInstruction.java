package ut.pp.SPRIL;

import ut.pp.EDSL.ForkVar;

import java.util.ArrayList;
import java.util.List;

/**
 * Placeholder put in place when fork statements are turned into SPRIL code
 * Program.toSpril later calls setJumpTargetPtr to provide the threadId and jumpTarget and generate the instructions
 * that are represented by this placeholder
 * These instructions will then replace the placeholder in Finalizer.replacePlaceholders
 */
public class ForkPlaceholderInstruction extends Instruction {
    private ForkVar thread;
    private List<Instruction> instructions;

    /**
     * Constructor
     * @param thread identifier of the thread
     */
    public ForkPlaceholderInstruction(ForkVar thread) {
        this.thread = thread;
    }

    /**
     * Get the name of the thread
     * @return thread name
     */
    public String getThreadName() {
        return thread.getId();
    }

    /**
     * Generate instructions for forking to a new thread
     * @param jumpTarget    instruction line number where the code of the thread starts
     */
    public void setJumpTargetPtr(Instruction jumpTarget) {
        instructions = new ArrayList<>();
        instructions.add(new Instruction("Fork to " + thread.getId(), OpCode.LOAD, new TargetPtrVal(jumpTarget), new RegAddr("regA")));
        instructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("regA"), new DirAddr(thread.getAddr())));
    }

    /**
     * Get the instructions that for forking to a new thread
     * Requires setJumpTargetPtr to be called first
     * @return instructions for fork
     */
    public List<Instruction> getInstructions() {
        return this.instructions;
    }

    /**
     * Returns the Fork instruction with the two instructions it represents
     * For debugging purposes, when displaying code from the intermediate representation
     * @return Fork instruction as string
     */
    @Override
    public String toString() {
        if (instructions == null)
            return String.format("Fork (%s)", thread.getId());
        else
            return String.format("Fork: {\n %s,\n %s}", instructions.get(0), instructions.get(1));
    }
}
