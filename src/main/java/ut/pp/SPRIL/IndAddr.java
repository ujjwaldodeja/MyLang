package ut.pp.SPRIL;

/**
 * Class IndAddr represents SPRIL instruction argument for addressing a memory address
 * equal to the value of a register
 */
public class IndAddr extends Argument {
    private String reg;

    /**
     * Constructor
     * @param reg register that holds the memory address
     */
    public IndAddr(String reg) {
        this.type = Operand.ADDR_IM_DI;
        this.reg = reg;
    }

    /**
     * SPRIL instruction argument as string
     * @return argument as string
     */
    @Override
    public String toString() {
        return String.format("(IndAddr %s)", reg);
    }
}
