package ut.pp.SPRIL;

/**
 * Class Ind represents SPRIL instruction argument for relative targets
 */
public class Rel extends Argument {
    private int address;

    /**
     * constructor
     * @param address relative memory address of instruction to be jumped to
     */
    public Rel(int address) {
        this.type = Operand.TARGET;
        this.address = address;
    }

    /**
     * SPRIL instruction argument as string
     * @return argument as string
     */
    @Override
    public String toString() {
        return String.format("(Rel %s)", address >= 0 ? address : "("+address+")");
    }
}
