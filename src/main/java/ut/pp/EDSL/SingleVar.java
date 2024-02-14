package ut.pp.EDSL;

/**
 * Class SingleVar represents a single variable, this can also be a single index in an array
 */
public class SingleVar extends Variable {
    private Expression addressOffset = null;
    int maxArrayIndex;

    public SingleVar(String id, Types type, boolean hasValue, int addr, boolean shared) {
        super(id, type, hasValue, addr, shared);
    }
    public SingleVar(String id, Types type, boolean hasValue, int addr) {
        super(id, type, hasValue, addr, false);
    }

    public SingleVar(String id, Types type, boolean hasValue, int addr, Expression addressOffset, int maxArrayIndex) {
        this(id, type, hasValue, addr);
        this.addressOffset = addressOffset;
        this.maxArrayIndex = maxArrayIndex;
    }

    public Expression getAddressOffset() {
        return addressOffset;
    }

    public int getMaxArrayIndex() {
        return maxArrayIndex;
    }
}
