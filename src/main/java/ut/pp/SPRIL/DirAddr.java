package ut.pp.SPRIL;

/**
 * Class DirAddr represents SPRIL instruction argument for addressing a direct memory location
 */
public class DirAddr extends Argument {
    private int address;

    /**
     * Constructor
     * @param address direct address in memory
     */
    public DirAddr(int address) {
        this.type = Operand.ADDR_IM_DI;
        this.address = address;
    }

    /**
     * Get direct memory address in memory
     * @return address
     */
    public int getAddress() {
        return address;
    }

    /**
     * Spril DirAddr argument as string
     * @return argument as string
     */
    @Override
    public String toString() {
        return String.format("(DirAddr %d)", address);
    }
}
