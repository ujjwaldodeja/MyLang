package ut.pp.EDSL;

import ut.pp.SPRIL.ImmValue;
import ut.pp.SPRIL.Instruction;
import ut.pp.SPRIL.OpCode;
import ut.pp.SPRIL.RegAddr;

import java.util.ArrayList;
import java.util.List;

/**
 * Class LoadConst holds the necessary information to generate spril code that can load a constant value
 * such as 5 or true into a register
 */
public class LoadConst extends Expression {
    private int value;

    /**
     * LoadConst constructor
     * @param value     value of the const
     */
    public LoadConst(int value) {
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    /**
     * Generate spril code that can be run to load the given const value into the targetReg
     * given by superclass Expression
     * @return Spril Instruction list
     */
    @Override
    public List<Instruction> toSpril() {
        List<Instruction> instructions = new ArrayList<>();
        // load value into targetReg
        instructions.add(new Instruction(OpCode.LOAD, new ImmValue(value), targetReg));

        // push result to stack
        if (pushResultToStack) {
            instructions.add(new Instruction(OpCode.PUSH, targetReg));
        }

        return instructions;
    }
}
