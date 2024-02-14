package ut.pp.SPRIL;

/**
 * In the intermediate representation, target memory addresses for jump operations are represented by pointers
 * to the Instruction object that will be jumped to. All TargetPtrs will be converted to actual numerical
 * values in the final step of compilation
 */
public class TargetPtr extends Argument {
    private Instruction target;

    /**
     * Constructor
     * @param target Target instruction
     */
    public TargetPtr(Instruction target) {
        this.type = Operand.TARGET;
        this.target = target;
    }

    /**
     * Get the target instruction
     * @return target instruction
     */
    public Instruction getTarget() {
        return target;
    }

    /**
     * Returns the target of the targetPtr as if it were a SPRIL instruction argument
     * For debugging purposes when printing the intermediate representation
     * @return targetPtr as SPRIL argument
     */
    @Override
    public String toString() {
        return String.format("(Target: %s)", target.toString());
    }
}
