package ut.pp.EDSL;

/**
 * Abstract class that has all information about a variable.
 * It has 2 subclasses, for the 2 types of variables: single variables and arrays
 */
public abstract class Variable {
    private String id;
    private Types type;
    private boolean shared;
    private boolean hasValue;
    private boolean copy;
    private int addr;

    /**
     * Constructor
     *
     * @param addr     the address in memory
     * @param hasValue flag that shows if value was initialized or not
     * @param id       name of the variable
     * @param shared   flag that shows if variable is shared or not
     * @param type     type of the variable (boolean/int/..)
     */
    public Variable(String id, Types type, boolean hasValue, int addr, boolean shared) {
        this.id = id;
        this.type = type;
        this.hasValue = hasValue;
        this.copy = false;
        this.addr = addr;
        this.shared = shared;
    }

    /**
     * Getters and setters for the variables of this class
     */
    public int getAddr() {
        return addr;
    }

    public void setAddr(int addr) {
        this.addr = addr;
    }

    public String getId() {
        return this.id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Types getType() {
        return type;
    }

    public void setType(Types type) {
        this.type = type;
    }

    public boolean hasValue() {
        return hasValue;
    }

    public void setHasValue(boolean hasValue) {
        this.hasValue = hasValue;
    }

    public boolean isCopy() {
        return copy;
    }

    public void setCopy(boolean copy) {
        this.copy = copy;
    }

    public boolean isShared() {
        return shared;
    }

    public void setShared(boolean shared) {
        this.shared = shared;
    }

    /**
     * Creates and returns a deep copy of a variable
     *
     * @param var variable from which the copy is generated
     * @return a new variable object with with the flag isCopy set to true
     */
    public static Variable getCopy(Variable var) {
        boolean isSingleVal = var.isSingleVarClass(); // get type of subclass
        Variable newVar;
        if (isSingleVal) {
            SingleVar singleVar = (SingleVar) var;
            newVar = new SingleVar(singleVar.getId(), singleVar.getType(), singleVar.hasValue(), singleVar.getAddr(), singleVar.isShared());
        } else {
            ArrayVar arrVar = (ArrayVar) var;
            newVar = new ArrayVar(arrVar.getId(), arrVar.getType(), arrVar.getLength(), arrVar.hasValue(), arrVar.getAddr(), arrVar.isShared());
        }
        newVar.setCopy(true);
        return newVar;
    }

    /**
     * Checker for the subtype of the variable
     *
     * @return true if variable's subclass is SingleVar and false otherwise
     */
    public boolean isSingleVarClass() {
        return SingleVar.class.isAssignableFrom(this.getClass());
    }

    /**
     * Converts variable to string. The string contains the name of the variable and the address
     *
     * @return the string representation variable
     */
    @Override
    public String toString() {
        return String.format("var: %s, address: %s ", getId(), getAddr());
        /* uncomment line below to get more descriptive representation of the variable */
        // return String.format("var: %s, hasValue: %s, address: %s, is copy %s", getId(), hasValue(), getAddr(), isCopy());
    }
}
