package ut.pp.check;

import ut.pp.EDSL.ArrayVar;
import ut.pp.EDSL.SingleVar;
import ut.pp.EDSL.Types;
import ut.pp.EDSL.Variable;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Store and tests for nested scopes of variable declarations.
 * A newly constructed {@link SymbolTable} should consist of a single
 * (outer) scope.
 */
public class SymbolTable {
    private Scope mainScope;
    private Scope currentScope;

    public SymbolTable() {
        this.mainScope = new Scope(null, "global", 0);
        currentScope = mainScope;
    }

    /**
     * Creates a new scope and adds copies of all the variables
     * of the current scope to the newly constructed scope.
     * Also, it sets the address field of the new scope to the last
     * available address of the current scope
     *
     * @param name the name of the new scope
     */
    public void openScope(String name) {
        Scope newScope = new Scope(currentScope, name, 0);
        for (Variable var : currentScope.getVars()) {
            newScope.addVar(Variable.getCopy(var));
        }
        newScope.setCurrentAddr(currentScope.getCurrentAddr());

        this.currentScope.addScope(newScope);
        currentScope = newScope;
    }

    /**
     * Closes previously opened scope. The main scope in never closed.
     * It gets all the copy variables and transfers them to the main scope
     * (because their value may be changed)
     */
    public void closeScope() {
        if (currentScope == mainScope) {
            throw new RuntimeException("Error in SymbolTable.closeScope(), cannot close global scope");
        }
        for (Variable var : currentScope.getVars()) {
            if (var.isCopy()) {
                updateParentVar(var);
            }
        }
        currentScope = currentScope.getParentScope();
    }

    /**
     * Creates and adds a single variable object in the scope
     * if the variable is already created and it's a copy, then it updates that variable
     *
     * @param id       name of the variable
     * @param type     type of the variable
     * @param hasValue flag that shows if variable is initialized or not
     * @param isShared flag that shows if variable is shared or not
     * @return true if variable is created/updated; false otherwise
     */
    public boolean addSingleVar(String id, Types type, boolean hasValue, boolean isShared) {
        if (checkCurrentScope(id)) {
            currentScope.addVar(new SingleVar(id, type, hasValue, currentScope.getNextAddr(), isShared));
            return true;
        } else if (getVar(id).isCopy()) {
            Variable var = getVar(id);
            updateParentVar(var);
            var.setType(type);
            var.setCopy(false);
            var.setHasValue(hasValue);
            var.setAddr(currentScope.getNextAddr());
            var.setShared(isShared);
            return true;
        }
        return false;
    }

    /**
     * Updates the
     * e.g. if inside and while statement we assign
     * a value to a variable that was declared in main, we have to update
     * the hasValue flag of the variable in te main scoep
     *
     * @requires var.isCopy() == true
     */
    private void updateParentVar(Variable var) {
        Variable parVar = currentScope.getParentScope().getVar(var.getId());
        parVar.setHasValue(var.hasValue());
    }

    /**
     * Creates and adds a array variable object in the scope
     * if the variable is already created and it's a copy, then it updates that variable
     *
     * @param id       name of the variable
     * @param type     type of the variable
     * @param length   of the array
     * @param hasValue flag that shows if variable is initialized or not
     * @param isShared flag that shows if variable is shared or not
     * @return true if variable is created/updated; false otherwise
     */
    public boolean addArrVar(String id, Types type, int length, boolean hasValue, boolean isShared) {
        if (checkCurrentScope(id)) {
            currentScope.addVar(new ArrayVar(id, type, length, hasValue, currentScope.getNextAddrArr(length), isShared));
            return true;
        } else if (getVar(id).isCopy()) {
            Variable var = getVar(id);
            if (!var.isSingleVarClass()) {
                ArrayVar arrVar = (ArrayVar) var;
                updateParentVar(var);
                arrVar.setType(type);
                arrVar.setHasValue(hasValue);
                arrVar.setAddr(currentScope.getNextAddrArr(length));
                arrVar.setCopy(false);
                arrVar.setLength(length);
                arrVar.setShared(isShared);
                return true;
            }
        }
        return false;
    }

    /**
     * @param id the name of the variable
     * @return true if current scope has a variable with the given id
     */
    public boolean checkCurrentScope(String id) {
        return !currentScope.contains(id);
    }

    /**
     * @param id the name of the variable
     * @returns the id of the scope that contains a variable with given id or null otherwise
     */
    public String getScopeName(String id) {
        for (Scope s = currentScope; s != null; s = s.getParentScope()) {
            if (s.contains(id)) {
                return s.getName();
            }
        }
        return null;
    }

    public Scope getMainScope() {
        return mainScope;
    }

    /**
     * @return true if variable with name id is contained in any of the scopes of the symbol table
     */
    public boolean contains(String id) {
        for (Scope s = currentScope; s != null; s = s.getParentScope()) {
            if (s.contains(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Getter for variables in the symbol table
     *
     * @param id the name of the variable
     * @return the variable object or null if its not found
     */
    public Variable getVar(String id) {
        for (Scope s = currentScope; s != null; s = s.getParentScope()) {
            if (s.contains(id)) {
                return s.getVar(id);
            }
        }
        return null;
    }

    /**
     * Adds a list of shared variables in a symbol table
     * Used when a fork is created and the fork needs to have
     * all the shared variables that were previously defined
     *
     * @param list of shared variables
     */
    public void addSharedVars(List<Variable> list) {
        for (Variable var : list) {
            this.currentScope.addVar(var);
        }
    }

    /**
     * Getter for shared variables
     * Given the defined grammar, the shared variables are declared always in the mains scope
     *
     * @return a list of all variables with shared flag set to true
     */
    public List<Variable> getSharedVars() {
        return getMainScope().getSharedVars();
    }

    /**
     * Provides a string representation of the symbol table
     *
     * @return a string that shows all the scopes and their variables
     */
    @Override
    public String toString() {
        return "< Symbol table >\n" + mainScope.toString();
    }
}
