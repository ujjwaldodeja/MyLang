package ut.pp.SPRIL;

import java.util.Arrays;
import java.util.List;

/**
 * Class Instruction represents a SPRIL instruction, it has an OpCode, and a number of arguments
 * Additionally, it can contain a comment
 */
public class Instruction {
    private OpCode opCode;
    private List<Argument> args;
    private String comment = "";

    /**
     * Default constructor, does nothing
     */
    protected Instruction() {}

    /**
     * Constructor with comment
     * Ensures the arguments are of valid types for the operation
     * @param comment   comment
     * @param opCode    opCode
     * @param arguments any number of arguments
     */
    public Instruction(String comment, OpCode opCode, Argument... arguments) {
        this(opCode, arguments);
        this.comment = "--"+comment;
    }

    /**
     * Constructor without comment
     * Ensures the arguments are of valid types for the operation
     * @param opCode    opCode
     * @param arguments any number of arguments
     */
    public Instruction(OpCode opCode, Argument... arguments) {
        this.opCode = opCode;
        args = Arrays.asList(arguments);
        List<Operand> expectedOperands = opCode.getOperands();

        // Check if the right number of arguments has been provided
        if (args.size() != expectedOperands.size()) {
            throw new IllegalArgumentException("Expected " + expectedOperands.size() + " arguments, but received " + args.size());
        }

        // Check if the types of the arguments are as expected
        for (int i = 0; i < args.size(); i++) {
            Argument arg = args.get(i);
            Operand expectedType = expectedOperands.get(i);
            if (arg.getType() != expectedType) {
                throw new IllegalArgumentException("Operation " + opCode + ", argument " + i + " should be of type " + expectedType + " but is " + arg.getType());
            }
        }
    }

    /**
     * Add a comment to the instruction
     * If a comment already exists, it gets added with the separator //
     * @param comment comment
     */
    public void addComment(String comment) {
        if (this.comment == "") this.comment = "--" + comment;
        else this.comment += " // " + comment;
    }

    /**
     * Get the OpCode of the instruction
     * @return opcode
     */
    public OpCode getOpCode() {
        return opCode;
    }

    /**
     * Get an argument of the instruction
     * @return argument
     */
    public Argument getArg(int index) {return args.get(index);}

    /**
     * If the instruction has a TargetPtr as argument, return the instruction that can be jumped to
     * @return targetPtr or null
     */
    public Instruction getJumpTargetPtr() {
        Argument jumpArg;
        if (opCode == OpCode.BRANCH) {
            jumpArg = args.get(1);
        }
        else if (opCode == OpCode.JUMP) {
            jumpArg = args.get(0);
        }
        else if (opCode == OpCode.LOAD) {
            // If the operation is LOAD, it could load a TargetPtrVal for a different thread to branch to
            if (args.get(0) instanceof TargetPtrVal) {
                jumpArg = args.get(0);
            }
            else return null;
        }
        else return null;

        if (jumpArg instanceof TargetPtr) {
            return ((TargetPtr) jumpArg).getTarget();
        }
        else if (jumpArg instanceof TargetPtrVal) {
            return ((TargetPtrVal) jumpArg).getTarget();
        }
        else {
            return null;
        }
    }

    /**
     * If an instruction is a load with a TargetPtr, or a jump or branch, then we update the target to a TargetPtr pointing
     * to the target Instruction
     * @param target target Instruction
     */
    public void setJumpTargetPtr(Instruction target) {
        if (opCode == OpCode.BRANCH) {
            TargetPtr targetPtr = new TargetPtr(target);
            args.set(1, targetPtr);
        }
        else if (opCode == OpCode.JUMP) {
            TargetPtr targetPtr = new TargetPtr(target);
            args.set(0, targetPtr);
        }
        else if (opCode == OpCode.LOAD) {
            if (args.get(0) instanceof TargetPtrVal) {
                TargetPtrVal targetPtr = new TargetPtrVal(target);
                args.set(0, targetPtr);
            }
            else {
                System.err.println("Error: Instruction.setJumpTargetPtr called, but Load does not have a TargetPtr");
            }
        }
        else {
            System.err.println("Error: Instruction.setJumpTargetPtr called, but instruction is not a Jump, Branch or load with TargetPtr");
        }
    }

    /**
     * If an instruction is a load with a TargetPtr, or a jump or branch, then we update the target to an absolute memory address
     * @param address absolute memory address of target instruction
     */
    public void setJumpTargetAbs(int address) {
        if (opCode == OpCode.BRANCH) {
            Argument absTarget = new Abs(address);
            args.set(1, absTarget);
        }
        else if (opCode == OpCode.JUMP) {
            Argument absTarget = new Abs(address);
            args.set(0, absTarget);
        }
        else if (opCode == OpCode.LOAD) {
            Argument absTarget = new ImmValue(address);
            if (args.get(0) instanceof TargetPtrVal) {
                args.set(0, absTarget);
            }
            else System.err.println("Error: Instruction.setJumpTargetAbs called, but Load does not have a TargetPtr");
        }
        else {
            System.err.println("Error: Instruction.setJumpTargetAbs called, but instruction is not a jump or branch");
        }
    }

    /**
     * SPRIL instruction as string, with comment if this has been set
     * @return instruction as string
     */
    @Override
    public String toString() {
        String instructionStr = "";
        instructionStr += opCode.toString();
        for (Argument arg: args) {
            instructionStr += " ";
            instructionStr += arg.toString();
        }
        return String.format("%-40s %s", instructionStr, comment);
    }
}
