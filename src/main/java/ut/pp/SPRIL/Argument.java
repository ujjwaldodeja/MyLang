package ut.pp.SPRIL;

/**
 * Superclass of all arguments in spril instructions
 */
public abstract class Argument {
    // numberIO is a special argument of type AddrImDi
    public final static Argument NUMBER_IO = new Argument() {
        @Override
        public Operand getType() {
            return Operand.ADDR_IM_DI;
        }

        @Override
        public String toString() {
            return "numberIO";
        }
    };

    public static Argument CHAR_IO = new Argument() {
        @Override
        public Operand getType() {
            return Operand.ADDR_IM_DI;
        }

        @Override
        public String toString() {
            return "charIO";
        }
    };

    protected Operand type;

    /**
     * Get the type of the argument (OPERATOR, REG_ADDR, TARGET, ADDR_IM_DI, STRING)
     * @return argument type
     */
    public Operand getType() {
        return this.type;
    }
}
