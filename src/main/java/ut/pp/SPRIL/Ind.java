package ut.pp.SPRIL;

/**
 * Class Ind represents SPRIL instruction argument for indirect targets
 */
public class Ind extends Argument {
    private String reg;

    /**
     * constructor
     * @param reg relative memory address of instruction to be jumped to
     */
    public Ind(String reg) {
        this.type = Operand.TARGET;
        this.reg = reg;
    }

    /**
     * SPRIL instruction argument as string
     * @return argument as string
     */
    @Override
    public String toString() {
        return String.format("(Ind %s)", reg);
    }
}
