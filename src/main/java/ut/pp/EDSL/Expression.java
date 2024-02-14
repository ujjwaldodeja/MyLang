package ut.pp.EDSL;

import ut.pp.SPRIL.Instruction;
import ut.pp.SPRIL.RegAddr;

import java.util.List;

/**
 * Class Expression is the superclass of all expressions.
 */
public abstract class Expression {
    protected RegAddr targetReg = new RegAddr("regA");
    protected boolean pushResultToStack = false;

    /**
     * Set the register where the resulting value of the expression can be read after the generated spril code
     * for this expression was ran
     * @param targetReg     Target Register
     */
    public void setTargetReg(String targetReg) {
        this.targetReg = new RegAddr(targetReg);
    }

    /**
     * Configure if the resulting value of the expression should be pushed onto the stack or not
     * @param pushResultToStack
     */
    public void setPushResultToStack(boolean pushResultToStack) {
        this.pushResultToStack = pushResultToStack;
    }

    /**
     * Generate spril code that calculates the value of the expression
     * @return Spril instruction list
     */
    public abstract List<Instruction> toSpril();
}
