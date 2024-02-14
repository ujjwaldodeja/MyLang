package ut.pp.SPRIL;

/**
 * In the intermediate representation, target memory addresses for jump operations are represented by pointers
 * to the Instruction object that will be jumped to. All TargetPtrs will be converted to actual numerical
 * values in the final step of compilation
 *
 * For implementing Fork, we have to write the value of which instruction the thread should jump to
 * This value is numeric and is of type AddrImDi, therefore we cannot use the TargetPtr to represent the argument
 * Instead we use TargetPtrVal which has the same behaviour
 */
public class TargetPtrVal extends TargetPtr {
    public TargetPtrVal(Instruction target) {
        super(target);
        this.type = Operand.ADDR_IM_DI;
    }
}
