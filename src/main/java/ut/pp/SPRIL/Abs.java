package ut.pp.SPRIL;

/**
 * Class Abs represents SPRIL instruction argument for absolute targets
 */
public class Abs extends Argument {
    private int address;

    /**
     * Constructor
     * @param address memory address of absolute jump target
     */
    public Abs(int address) {
        this.type = Operand.TARGET;
        this.address = address;
    }

    /**
     * Spril Abs argument as string
     * @return argument as string
     */
    @Override
    public String toString() {
        return String.format("(Abs %s)", address);
    }
}
