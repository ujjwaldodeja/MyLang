package ut.pp.EDSL;

import ut.pp.SPRIL.Instruction;
import ut.pp.check.SymbolTable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class representing the act of reading an array variable from memory in the EDSL
 * Does not contain a toSpril method because an array cannot be loaded into registers
 */
public class LoadArrayVar extends Expression {
    ArrayVar var;

    /**
     * Constructor
     * @param var arrayVar
     */
    public LoadArrayVar(ArrayVar var) {
        this.var = var;
    }

    /**
     * Get a List of LoadVar expressions to load the individual variables of the array into memory
     * @return LoadVar expressions
     */
    public List<LoadVar> getLoadExpressions() {
        List<LoadVar> loadExpressions = new ArrayList<>();
        int arrayBaseAddress = var.getAddr();
        for (int addressOffset = 0; addressOffset < var.getLength(); addressOffset++) {
            int individualAddress = arrayBaseAddress + addressOffset;
            SingleVar individualVar = new SingleVar(var.getId(), var.getType(), var.hasValue(), individualAddress);
            loadExpressions.add(new LoadVar(individualVar));
        }
        return loadExpressions;
    }

    /**
     * This method does nothing, as an array cannot be loaded into registers
     * @return null
     */
    @Override
    public List<Instruction> toSpril() {
        System.err.println("Error: ToSpril cannot be called on EDSL.LoadArrayVar");
        return null;
    }
}
