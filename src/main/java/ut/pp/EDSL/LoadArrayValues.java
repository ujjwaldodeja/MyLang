package ut.pp.EDSL;

import ut.pp.SPRIL.Instruction;

import java.util.List;

/**
 * Class representing the assignment of constant values in an array in the EDSL
 * Does not contain a toSpril method because an array cannot be loaded into registers
 */
public class LoadArrayValues extends Expression {
    private List<LoadConst> values;

    /**
     * Constructor
     * @param values List of LoadConst values in the array
     */
    public LoadArrayValues(List<LoadConst> values) {
        this.values = values;
    }

    /**
     * Get the length of the array
     * @return length
     */
    public int getLength() {
        return values.size();
    }

    /**
     * Get all the LoadConst expressions for the values
     * @return LoadConst expressions
     */
    public List<LoadConst> getLoadExpressions() {
        return this.values;
    }

    /**
     * This method does nothing, as an array cannot be loaded into registers
     * @return null
     */
    @Override
    public List<Instruction> toSpril() {
        System.err.println("Error: ToSpril cannot be called on EDSL.LoadArrayValues");
        return null;
    }
}
