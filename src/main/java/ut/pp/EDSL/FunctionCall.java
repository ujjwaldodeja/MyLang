package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class FunctionCall holds all the necessary information to compile a FunctionCall
 * FunctionCalls have the format [functionName]([arguments])
 * Currently only the function call print([value]) is supported
 */
public class FunctionCall implements Statement {
    private FunctionCall.Function funcName;
    private List<Expression> arguments;

    /**
     * FunctionCall constructor
     * @param funcName      The function name
     * @param arguments     List of arguments
     */
    public FunctionCall(FunctionCall.Function funcName, List<Expression> arguments) {
        this.funcName = funcName;
        this.arguments = arguments;
    }

    /**
     * Enum containing all possible function names
     * (Currently only PRINT is supported)
     */
    public enum Function {
        PRINT;
    }

    /**
     * Generate spril code that can be executed to perform the function call
     * @return List of spril instructions
     */
    @Override
    public List<Instruction> toSpril() {
        if (funcName == Function.PRINT) {
            List<Instruction> instructions = new ArrayList<>();

            // Generate Sprockell code to store result of argument (expression) into register A
            Expression arg0 = arguments.get(0);
            arg0.setTargetReg("regA");
            instructions.addAll(arg0.toSpril());

            // Write register A content to Number IO
            instructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("regA"), Argument.NUMBER_IO));
            instructions.get(0).addComment("Print");
            return instructions;
        }
        return null;
    }
}
