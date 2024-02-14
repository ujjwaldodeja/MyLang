package ut.pp.EDSL;

/**
 * Class ArrayVar represents an array variable. In addition to all
 * variables of class Variable, ArrayVar contains also the
 * length of the declared array
 */
public class ArrayVar extends Variable {
    /**
     * Length of the declared array
     */
    private int length;

    /**
     * Constructor
     *
     * @param id       name of the variable
     * @param type     type of the variable (boolean/int/..)
     * @param length   length of declared array
     * @param hasValue flag that shows if value was initialized or not
     * @param addr     the address in memory
     * @param shared   flag that shows if variable is shared or not
     */
    public ArrayVar(String id, Types type, int length, boolean hasValue, int addr, boolean shared) {
        super(id, type, hasValue, addr, shared);
        this.length = length;
    }

    /**
     * Getter and setter for the length
     */
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
}
