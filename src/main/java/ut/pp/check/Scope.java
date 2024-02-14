package ut.pp.check;

import ut.pp.EDSL.Variable;

import java.util.ArrayList;
import java.util.List;

/**
 * Class that stores all the relevant information about a scope.
 * All the scopes are structured in a tree-like data structure.
 * Each scope stores the parent and a tree of children
 */
public class Scope {
    /**
     * List of variables that are contained in the scope
     */
    private final List<Variable> vars;
    /**
     * List of child scopes of the current scope
     */
    private final List<Scope> childScopes;
    private final Scope parentScope;
    /**
     * Name of the scope (main/if/...)
     */
    private final String name;
    /**
     * The address that is available for the variable to be assigned at
     */
    int currentAddr;

    /**
     * Constructor
     *
     * @param currentAddr the first available address in the scope
     * @param name        name of the scope
     * @param parentScope the parent of the scope
     */
    public Scope(Scope parentScope, String name, int currentAddr) {
        this.vars = new ArrayList<>();
        this.name = name;
        this.currentAddr = currentAddr;
        this.parentScope = parentScope;
        this.childScopes = new ArrayList<>();
    }

    /**
     * Checks if a variable with a given id is already contained in the string
     *
     * @param id the name of the variable
     * @return true if the variable with that name
     * is already contained in scope and false otherwise
     */
    public boolean contains(String id) {
        for (Variable v : vars) {
            if (v.getId().equals(id)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Adds a variable object to the scope
     */
    public void addVar(Variable var) {
        vars.add(var);
    }

    /**
     * Adds a child scope
     */
    public void addScope(Scope scope) {
        childScopes.add(scope);
    }

    /**
     * Getter for a value with a given id.
     *
     * @param id the name of the variable
     * @return the variable object with the required id or null if no variable with that id is declared in scope
     */
    public Variable getVar(String id) {
        for (Variable v : vars) {
            if (v.getId().equals(id)) {
                return v;
            }
        }
        return null;
    }

    /**
     * Converts scope to string. The string contains the scope name
     * and a list-like representation all the variables defined in the scope.
     *
     * @return the string representation a scope
     */
    @Override
    public String toString() {
        StringBuilder str = new StringBuilder();
        str.append("Scope " + name + ":\n");
        str.append("[");
        for (Variable var : vars) {
            str.append(var);
            str.append(" | ");
        }
        str.append("]\n");
        for (Scope scope : childScopes) {
            str.append(scope);
            str.append("\n");
        }
        str.append("(End of scope " + name + ")");
        return str.toString();
    }

    /**
     * Getters and setters
     */
    public String getName() {
        return name;
    }

    public List<Variable> getVars() {
        return vars;
    }

    public int getCurrentAddr() {
        return currentAddr;
    }

    public void setCurrentAddr(int currentAddr) {
        this.currentAddr = currentAddr;
    }

    public int getNextAddr() {
        int current = currentAddr;
        currentAddr += 1;
        return current;
    }

    public Scope getParentScope() {
        return parentScope;
    }

    public Scope getChildScope(int index) {
        return childScopes.get(index);
    }

    /**
     * Returns the current available address and updates the current available address
     *
     * @param length length of a variable
     *               (e.g. for single values or arrays with one element, this is 1)
     * @return an available address in the scope
     */
    public int getNextAddrArr(int length) {
        int current = currentAddr;
        currentAddr += length;
        return current;
    }

    /**
     * Getter for shared variables
     *
     * @return list with all the shared variables in the scope
     */
    public List<Variable> getSharedVars() {
        List<Variable> sharedVars = new ArrayList<>();
        for (Variable v : vars) {
            if (v.isShared()) {
                sharedVars.add(v);
            }
        }
        return sharedVars;
    }
}
