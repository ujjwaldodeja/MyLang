package ut.pp.SPRIL;

/**
 * Class ImmValue represents SPRIL argument for using a constant value
 */
public class ImmValue extends Argument {
    private int value;

    /**
     * Constructor
     * @param value value
     */
    public ImmValue(int value) {
        this.type = Operand.ADDR_IM_DI;
        this.value = value;
    }

    /**
     * Get the value
     * @return value
     */
    public int getValue() {
        return value;
    }

    /**
     * Spril argument as string
     * @return argument as string
     */
    @Override
    public String toString() {
        String str = String.format("(ImmValue %s)", (value >= 0) ? value : "("+value+")");
        return str;
    }
}
