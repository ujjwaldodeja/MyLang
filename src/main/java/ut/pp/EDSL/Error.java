package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Enum containing all errors that can occur at runtime
 */
public enum Error {
    DivByZero("Cannot divide by 0"),
    ArrayIndexOutOfBounds("Attempting to access out of bounds index of array");

    private String msg;

    /**
     * Constructor
     * @param msg error message displayed to the user
     */
    private Error(String msg) {
        this.msg = "Error: " + msg;
    }

    /**
     * Get a numeric id representing the error
     * @return id
     */
    public int getId() {
        return this.ordinal();
    }

    /**
     * Generate SPRIL code that can be run to print the error message to the console
     * @return SPRIL instruction list
     */
    public List<Instruction> toSpril() {
        List<Instruction> instructions = new ArrayList<>();
        for (char c: msg.toCharArray()) {
            int ord = (int) c;
            instructions.add(new Instruction(OpCode.LOAD, new ImmValue(ord), new RegAddr("regA")));
            instructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("regA"), Argument.CHAR_IO));
        }
        instructions.get(0).addComment("Print error " + this.name());
        return instructions;
    }
}
