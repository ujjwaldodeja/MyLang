package ut.pp.SPRIL;

/**
 * Class Operator represents an operator in a SPRIL Compute operation
 */
public class Operator extends Argument {
    private String operator;

    /**
     * Constructor
     * @param operator name of the operator
     */
    public Operator(String operator) {
        this.type = Operand.OPERATOR;
        this.operator = operator;
    }

    /**
     * Get the operator
     * @return operator
     */
    public String getOperator() {
        return this.operator;
    }

    /**
     * Get the operator as SPRIL code
     * @return operator
     */
    @Override
    public String toString() {
        return operator;
    }
}
