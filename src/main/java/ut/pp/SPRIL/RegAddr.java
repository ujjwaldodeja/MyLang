package ut.pp.SPRIL;

/**
 * Class RegAddr represents SPRIL instruction argument for addressing a register
 */
public class RegAddr extends Argument {
    private String reg;

    /**
     * Constructor
     * @param reg register
     */
    public RegAddr(String reg) {
        this.type = Operand.REG_ADDR;
        this.reg = reg;
    }

    /**
     * Get the register address
     * @return register
     */
    public String getReg() {
        return reg;
    }

    /**
     * Get the SPRIL code for addressing the register
     * @return register
     */
    @Override
    public String toString() {
        return reg;
    }
}
