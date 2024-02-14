package ut.pp.SPRIL;

import java.util.Arrays;
import java.util.List;

/**
 * Enum containing all possible operations, and what arguments they take
 */
public enum OpCode {
    COMPUTE("Compute", Operand.OPERATOR, Operand.REG_ADDR, Operand.REG_ADDR, Operand.REG_ADDR),
    JUMP("Jump", Operand.TARGET),
    BRANCH("Branch", Operand.REG_ADDR, Operand.TARGET),
    LOAD("Load", Operand.ADDR_IM_DI, Operand.REG_ADDR),
    STORE("Store", Operand.REG_ADDR, Operand.ADDR_IM_DI),
    PUSH("Push", Operand.REG_ADDR),
    POP("Pop", Operand.REG_ADDR),
    READ_INSTR("ReadInstr", Operand.ADDR_IM_DI),
    RECEIVE("Receive", Operand.REG_ADDR),
    WRITE_INSTR("WriteInstr", Operand.REG_ADDR, Operand.ADDR_IM_DI),
    TEST_AND_SET("TestAndSet", Operand.ADDR_IM_DI),
    ENDPROG("EndProg"),
    NOP("Nop");

    private String code;
    private List<Operand> operands;

    /**
     * Enum constructor
     * @param code      the name of the Operation in SPRIL
     * @param operands  ordered list of arguments it requires
     */
    private OpCode(String code, Operand... operands) {
        this.code = code;
        this.operands = Arrays.asList(operands);
    }

    /**
     * Get the arguments for the operation
     * @return arguments
     */
    public List<Operand> getOperands() {
        return this.operands;
    }


    /**
     * Get the SPRIL code of the operation
     * @return SPRIL code of operation
     */
    @Override
    public String toString() {
        return this.code;
    }
}
