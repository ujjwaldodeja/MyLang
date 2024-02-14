package ut.pp.check;

import main.antlr4.ut.pp.parser.MyLangBaseVisitor;
import main.antlr4.ut.pp.parser.MyLangParser;
import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.tree.ParseTree;
import ut.pp.EDSL.*;

import java.util.ArrayList;
import java.util.List;

import java.util.*;

/**
 * Class to do type checking, scope checking, array usage checking and collect all the errors
 * The checker does not stop at the first error. It visits all the nodes and collects all the possible errors.
 */
public class Checker extends MyLangBaseVisitor {

    /**
     * List of errors collected in the latest call of {@link #getErrors}.
     */
    private final List<String> errors;
    /**
     * List of warnings collected in the latest call of {@link #getErrors}.
     */
    private final List<String> warnings;
    /**
     * Symbol table for the main thread.
     */
    private final SymbolTable mainThreadSymbolTable;
    /**
     * Result of the latest call of {@link #check}.
     */
    private final ResultTrees resultTrees;
    /**
     * Contains all the running threads
     */
    private final Stack<String> enteredThreads;
    /**
     * Maps symbol tables to thread names.
     */
    private final Map<String, SymbolTable> symbolTablePerThread;
    /**
     * Contains the symbol table for the current running thread
     */
    private SymbolTable curThreadSymbolTable;
    /**
     * Separate scope that contains thread and lock variables
     */
    private final Scope sharedMemoryLocations;

    /**
     * Constructor for Checker class, initializes all the lists and symbol tables
     */
    public Checker() {
        this.errors = new ArrayList<>();
        this.warnings = new ArrayList<>();
        this.mainThreadSymbolTable = new SymbolTable();
        this.resultTrees = new ResultTrees();
        this.enteredThreads = new Stack<>();
        this.symbolTablePerThread = new HashMap<>();
        this.curThreadSymbolTable = mainThreadSymbolTable;
        this.sharedMemoryLocations = new Scope(null, "shared", 0);
    }

    /**
     * Runs this checker on a given parse tree,
     * and returns the checker result.
     *
     * @param tree the parse tree that will be traversed
     * @return constructed parse tree properties for type and variable
     */
    public ResultTrees check(ParseTree tree) {
        visit(tree);
        return resultTrees;
    }

    /**
     * Getter for errors
     *
     * @return list of errors
     * @requires {@link #check} should be called beforehand
     */
    public List<String> getErrors() {
        return this.errors;
    }

    /**
     * Getter for warnings
     *
     * @return list of warnings
     * @requires {@link #check} should be called beforehand
     */
    public List<String> getWarnings() {
        return this.warnings;
    }

    /**
     * Visitor for Program
     * First visits shared variables, after that resets the address of the main scope to 0,
     * sets the starting address of the shared memory locations(forks and locks) to the last address of shared memory variables
     * and then visits the rest of nodes
     *
     * @return list of errors after all nodes are visited
     */
    @Override
    public List<String> visitProgram(MyLangParser.ProgramContext ctx) {
        visit(ctx.sharedVars());
        sharedMemoryLocations.setCurrentAddr(curThreadSymbolTable.getMainScope().getCurrentAddr());
        curThreadSymbolTable.getMainScope().setCurrentAddr(0);
        visit(ctx.statementList());
        return this.errors;
    }

    /**
     * Visits all statements in a statement list
     *
     * @return null
     */
    @Override
    public Object visitStatementList(MyLangParser.StatementListContext ctx) {
        for (MyLangParser.StatementContext stmt : ctx.statement()) {
            visit(stmt);
        }
        return null;
    }

    /**
     * Gets the type by visiting type node and then calls helper method {@link #declare} for the list of variables that is declared
     *
     * @return null
     */
    @Override
    public Object visitDeclaration(MyLangParser.DeclarationContext ctx) {
        Types type = (Types) visit(ctx.type());
        declare(type, ctx.varName(), ctx, false);
        return null;
    }

    /**
     * Helper function used for checking the declaration of a list of variable names and adding variables to the symbol table
     * The return value of visiting varName (see {@link #visitSingle} and {@link #visitArrayIndex})
     * is used in order to get more information about the kind of variable (array/ single var)
     * and also name of variable (and length if its an array)
     *
     * @param type     type of the declared variables
     * @param vars     list of variables that are declared
     * @param ctx      context of declaration - used for adding errors
     * @param isShared variables are shared memory or not
     */
    private void declare(Types type, List<MyLangParser.VarNameContext> vars, ParserRuleContext ctx, boolean isShared) {
        for (MyLangParser.VarNameContext varName : vars) {
            String[] var = (String[]) visit(varName);
            String varNameStr = var[0];
            String scope = curThreadSymbolTable.getScopeName(varNameStr);
            if (scope == null) scope = sharedMemoryLocations.getName();
            if (var.length == 1) { //var in an array of length 1 means that tis a single variable
                if (!curThreadSymbolTable.addSingleVar(varNameStr, type, false, isShared)
                        || sharedMemoryLocations.contains(varNameStr)) {
                    addError(ctx, "Variable %s is already defined in %s scope", varNameStr, scope);
                }
            } else { //var in an array of length 2 means that this is an array; second val of array is the length
                int length = Integer.parseInt(var[1]);
                if (length == 0) {
                    addError(ctx, "Cannot declare array %s with length 0", varNameStr);
                }
                if (!curThreadSymbolTable.addArrVar(varNameStr, type, length, false, isShared)
                        || sharedMemoryLocations.contains(varNameStr)) {
                    addError(ctx, "Variable %s is already defined in %s scope", varNameStr, scope);
                }
            }
            setVar(varName, curThreadSymbolTable.getVar(varNameStr));
        }
    }

    /**
     * Visitor for type int
     *
     * @return type int
     */

    @Override
    public Types visitIntType(MyLangParser.IntTypeContext ctx) {
        return Types.INT;
    }

    /**
     * Visitor for type bool
     *
     * @return type bool
     */
    @Override
    public Types visitBoolType(MyLangParser.BoolTypeContext ctx) {
        return Types.BOOL;
    }

    /**
     * Visitor for declaration and assignment
     * Uses helper functions {@link #assign} and {@link #declare}
     *
     * @return null
     */
    @Override
    public Object visitDeclarationAndAssignment(MyLangParser.DeclarationAndAssignmentContext ctx) {
        Types type = (Types) visit(ctx.type());
        String[] varName = (String[]) visit(ctx.varName());
        String varNameStr = varName[0];

        /*by visiting RHS we get a flag that is: -1 for undefined value,
         0 for single value, >0 for array (representing length) */
        int varFlag = (int) visit(ctx.expression());

        if (!checkTypeBool(ctx.expression(), type)) {
            type = getType(ctx.expression()); // used for error propagation
        }

        declare(type, new ArrayList<>(Collections.singletonList(ctx.varName())), ctx, false);
        Variable var = curThreadSymbolTable.getVar(varNameStr); //get the value declared above from symbol table

        /*index is always -1 because you cannot declare an element of an array
         * in declaration and assignment, for e.g. a[5] means always an array of length 5*/
        assign(var, varFlag, ctx, -1, type, ctx.varName());
        return null;
    }

    /**
     * Visitor for assignment
     * Uses helper functions {@link #assign}
     *
     * @return null
     */
    @Override
    public Object visitAssignment(MyLangParser.AssignmentContext ctx) {
        /*array that contains the id of the variable(and declared length in case of an array)*/
        String[] varName = (String[]) visit(ctx.varName());
        String varNameStr = varName[0]; // variable name (id) declared on the LHS
        if (curThreadSymbolTable.checkCurrentScope(varNameStr)) {
            addError(ctx, "Variable %s was not declared in this scope", varNameStr);
        } else {
            Variable var = curThreadSymbolTable.getVar(varNameStr);
            Types type = var.getType();
            int varFlag = (int) visit(ctx.expression());

            if (!checkTypeBool(ctx.expression(), type)) {
                type = getType(ctx.expression());
            }

            if (varName.length == 2) { // the length is 2 means that the LHS is an array
                int index = Integer.parseInt(varName[1]);
                assign(var, varFlag, ctx, index, type, ctx.varName());
            } else {
                assign(var, varFlag, ctx, -1, type, ctx.varName());
            }
        }
        return null;
    }

    /**
     * The main purpose of this function is to handle and check the LHS of
     * the assignment and call the appropriate checker for RHS
     *
     * @param var        the variable stored in the symbol table (previously declared; LHS)
     * @param varFlag    flag returned by visiting RHS: -1 for undefined value,
     *                   0 for single value, >0 for array (representing length)
     * @param ctx        context of assignment - used for adding errors
     * @param index      in case of assigning a value to an element of a array (LHS) (e.g. for a[5] = 1, index = 5)
     * @param type       type of the RHS
     * @param varNameCtx context of the LHS
     */
    private void assign(Variable var, int varFlag, ParserRuleContext ctx, int index, Types type, ParserRuleContext varNameCtx) {
        String varNameStr = var.getId();
        String scope = curThreadSymbolTable.getScopeName(varNameStr);
        boolean isArray = ArrayVar.class.isAssignableFrom(var.getClass()); //kind of LHS; checks if stored Variable's subtype is Array;
        if (varFlag == -1) { //RHS undefined
            addError(ctx, "Cannot assign to undefined expression");
        } else { //RHS properly defined
            if (isArray) { //LHS is array
                ArrayVar varArr = (ArrayVar) var;
                int actualLength = varArr.getLength(); // length of the LHS - the declared array
                if (index != -1) { //LHS is a value of array
                    if (index > actualLength - 1 || index < 0) {
                        addError(ctx, "Index %d out of bounds for array %s", index, varNameStr);
                    }
                    if (!varArr.hasValue()) {
                        addWarning(ctx, "Array %s not initialized", varNameStr);
                    }
                    checkAssSingleVal(ctx, varFlag);
                } else { //LHS is entire array
                    //in this case, the varFlag represents the length of the array on the RHS
                    checkAssArr(ctx, varNameStr, varFlag, scope, actualLength);
                }
            } else { //LHS single var
                checkAssSingleVal(ctx, varFlag);
            }
            var.setHasValue(true);
            setVar(varNameCtx, curThreadSymbolTable.getVar(varNameStr));
        }
    }

    /**
     * Visitor for the if clause
     * Checks if the type of the conditions is bool; opens the scope, visits the statements and closes the scope
     *
     * @return null
     */
    @Override
    public Object visitIfClause(MyLangParser.IfClauseContext ctx) {
        for (MyLangParser.ExpressionContext expr : ctx.expression()) {
            visit(expr);
            checkType(expr, Types.BOOL);
        }
        for (MyLangParser.StatementListContext stmt : ctx.statementList()) {
            curThreadSymbolTable.openScope("if");
            visit(stmt);
            curThreadSymbolTable.closeScope();
        }
        return null;
    }

    /**
     * Visitor for the while clause
     * Checks if the type of the conditions is bool; opens the scope, visits the statements and closes the scope
     *
     * @return null
     */
    @Override
    public Object visitWhileClause(MyLangParser.WhileClauseContext ctx) {
        visit(ctx.expression());
        checkType(ctx.expression(), Types.BOOL);

        curThreadSymbolTable.openScope("while");
        visit(ctx.statementList());
        curThreadSymbolTable.closeScope();

        return null;
    }

    /**
     * Visitor for fork
     * Checks if the id is unique, creates and adds fork variable to the sharedMemVars list.
     * Creates a new symbol table for the new thread and adds all shared variables from the
     * main thread
     * It looks in entered threads to determine what thread is currently running and
     * what symbol table should be the current one
     *
     * @return null
     */
    @Override
    public Object visitFork(MyLangParser.ForkContext ctx) {
        String threadName = ctx.ID().getText();
        List<Variable> sharedVars = curThreadSymbolTable.getSharedVars();
        if (sharedMemoryLocations.contains(threadName) || curThreadSymbolTable.contains(threadName)) {
            addError(ctx, "Cannot create fork with id %s: id already used", threadName);
        }
        Variable fork = new ForkVar(threadName, Types.FORK, false, sharedMemoryLocations.getNextAddr());
        sharedMemoryLocations.addVar(fork);
        setVar(ctx, fork);
        enteredThreads.push(threadName);
        symbolTablePerThread.put(threadName, new SymbolTable());
        curThreadSymbolTable = symbolTablePerThread.get(threadName);
        curThreadSymbolTable.addSharedVars(sharedVars); //adds all shared vars to the fork symbol table

        visit(ctx.statementList());

        enteredThreads.pop(); //thread is visited so thread deleted from stack
        if (enteredThreads.empty()) {
            curThreadSymbolTable = mainThreadSymbolTable;
        } else {
            curThreadSymbolTable = symbolTablePerThread.get(enteredThreads.peek());
        }
        return null;
    }

    /**
     * Visitor for the lock statement
     * Checks if any variable with lock id is already defined and
     * adds a new lock variable to the sharedMemVars list
     *
     * @return null
     */
    @Override
    public Object visitGetLock(MyLangParser.GetLockContext ctx) {
        String lockName = ctx.ID().getText();
        if (!sharedMemoryLocations.contains(lockName)) {
            sharedMemoryLocations.addVar(new LockVar(lockName, Types.LOCK, false, sharedMemoryLocations.getNextAddr()));
        }
        setVar(ctx, sharedMemoryLocations.getVar(lockName));
        return null;
    }

    /**
     * Visitor for release lock statement
     * Checks if a lock with that id was previously defined
     */
    @Override
    public Object visitReleaseLock(MyLangParser.ReleaseLockContext ctx) {
        String varName = ctx.ID().getText();
        if (!sharedMemoryLocations.contains(varName) || !(sharedMemoryLocations.getVar(varName) instanceof LockVar)) {
            addError(ctx, "Cannot release undefined lock with id %s", varName);
        } else {
            setVar(ctx, sharedMemoryLocations.getVar(varName));
        }
        return null;
    }

    /**
     * Visitor for join fork statement
     * Checks if a fork with that id was previously defined
     *
     * @return null;
     */
    @Override
    public Object visitJoin(MyLangParser.JoinContext ctx) {
        String varName = ctx.ID().getText();
        if (!sharedMemoryLocations.contains(varName) || !(sharedMemoryLocations.getVar(varName) instanceof ForkVar)) {
            addError(ctx, "Cannot join undefined fork with id %s", varName);
        } else {
            setVar(ctx, sharedMemoryLocations.getVar(varName));
        }
        return null;
    }

    /**
     * Visitor for shared variables list
     *
     * @return null
     */
    @Override
    public Object visitSharedVars(MyLangParser.SharedVarsContext ctx) {
        for (MyLangParser.SharedDeclarationContext decl : ctx.sharedDeclaration()) {
            visit(decl);
        }
        return null;
    }

    /**
     * Visitor for shared variables declaration
     * Uses {@link #declare} helper method to declare all variables in the list
     *
     * @return null
     */
    @Override
    public Object visitSharedDeclaration(MyLangParser.SharedDeclarationContext ctx) {
        Types type = (Types) visit(ctx.type());
        declare(type, ctx.varName(), ctx, true);
        return null;
    }

    /**
     * Visitor for print
     * Checks if the value that needs to be printed is valid
     *
     * @return null
     */
    @Override
    public Object visitPrint(MyLangParser.PrintContext ctx) {
        int varFlag = (int) visit(ctx.expression());
        if (varFlag == -1 || getType(ctx.expression()) == Types.ERROR) {
            addError(ctx, "Cannot print an undefined expression");
        } else if (varFlag > 0) {
            addError(ctx, "Cannot print arrays");
        }
        return null;
    }

    /**
     * Visitor for bool operation
     * Checks if the the types are appropriate and values are valid
     *
     * @return 0 (varFlag) because bool operation always results in a single value
     */
    @Override
    public Object visitBoolOp(MyLangParser.BoolOpContext ctx) {
        int varFlag1 = (int) visit(ctx.expression(0));
        int varFlag2 = (int) visit(ctx.expression(1));

        Types type1 = getType(ctx.expression(0));
        Types type2 = getType(ctx.expression(1));
        String operator = ctx.boolOperator().getText();

        if (type1 != Types.BOOL || type2 != Types.BOOL) {
            addError(ctx, "Operator %s cannot be applied to %s and %s", operator, type1, type2);
        } else {
            if (varFlag1 == varFlag2 && varFlag1 == 0) {
                setType(ctx, Types.BOOL);
            } else {
                addError(ctx, "Cannot apply boolean operation on array");
            }
        }
        return 0;
    }

    /**
     * Visitor for prefix operation
     * Checks if the the type is appropriate and values are valid
     *
     * @return 0 (varFlag) because prefix operation always results in a single value
     */
    @Override
    public Object visitPrefixOp(MyLangParser.PrefixOpContext ctx) {
        int varFlag1 = (int) visit(ctx.expression());
        Types type1 = getType(ctx.expression());

        String operator = ctx.prefixOperator().getText();
        boolean booleanOp = (boolean) visit(ctx.prefixOperator());
        /*if prefix is "!", then expression's type should be boolean and int otherwise*/

        if ((booleanOp && type1 != Types.BOOL) || (!booleanOp && type1 != Types.INT)) {
            addError(ctx, "Operator %s cannot be applied to %s", operator, type1);
        } else {
            if (varFlag1 == 0) {
                setType(ctx, type1);
            } else if (varFlag1 == -1) {
                addError(ctx, "Cannot apply operator %s undefined value", operator);
            } else {
                addError(ctx, "Cannot apply operator %s to array", operator);
            }
        }
        return 0;
    }

    /**
     * Visitor for multiplication operation
     * Checks if the the types are correct and values are valid
     *
     * @return 0 (varFlag) because multiplication operation always results in a single value
     */
    @Override
    public Object visitMultiplication(MyLangParser.MultiplicationContext ctx) {
        int varFlag1 = (int) visit(ctx.expression(0));
        int varFlag2 = (int) visit(ctx.expression(1));

        Types type1 = getType(ctx.expression(0));
        Types type2 = getType(ctx.expression(1));
        String operator = ctx.multOperator().getText();

        if (type1 != Types.INT || type2 != Types.INT) {
            addError(ctx, "Operator %s cannot be applied to %s and %s", operator, type1, type2);
        } else {
            if (varFlag1 == varFlag2 && varFlag1 == 0) {
                setType(ctx, Types.INT);
            } else {
                addError(ctx, "Cannot apply operator %s to array", operator);
            }
        }
        return 0;
    }

    /**
     * Visitor for brackets
     *
     * @return the value of the expression (varFlag)
     */
    @Override
    public Object visitBrackets(MyLangParser.BracketsContext ctx) {
        int varFlag = (int) visit(ctx.expression());
        setType(ctx, getType(ctx.expression()));
        return varFlag;
    }

    /**
     * Visitor for addition operation
     * Checks if the the types are correct and values are valid
     *
     * @return 0 (varFlag) because addition operation always results in a single value
     */
    @Override
    public Object visitAddition(MyLangParser.AdditionContext ctx) {
        int varFlag1 = (int) visit(ctx.expression(0));
        int varFlag2 = (int) visit(ctx.expression(1));

        Types type1 = getType(ctx.expression(0));
        Types type2 = getType(ctx.expression(1));
        String operator = ctx.addOperator().getText();

        if (type1 != Types.INT || type2 != Types.INT) {
            addError(ctx, "Operator %s cannot be applied to %s and %s", operator, type1, type2);
        } else {
            if (varFlag1 == varFlag2 && varFlag1 == 0) {
                setType(ctx, Types.INT);
            } else {
                addError(ctx, "Cannot apply boolean operation on array", varFlag1, varFlag2);
            }
        }
        return 0;
    }

    /**
     * Visitor for prefix operator
     * The return value is used to know the required type of the expression
     *
     * @return true if the operator is "!" and false otherwise
     */
    @Override
    public Object visitPrefixOperator(MyLangParser.PrefixOperatorContext ctx) {
        if (ctx.getText().equals("!")) {
            return true;
        }
        return false;
    }

    /**
     * Visitor for comparison operation
     * Checks if the the types are correct and values are valid
     * If the compare operation is equals or not equals, then arrays are allowed
     *
     * @return 0 (varFlag) because bool operation always results in a single value
     */
    @Override
    public Object visitComparison(MyLangParser.ComparisonContext ctx) {
        int varFlag1 = (int) visit(ctx.expression(0));
        int varFlag2 = (int) visit(ctx.expression(1));

        Types type1 = getType(ctx.expression(0));
        Types type2 = getType(ctx.expression(1));
        String operator = ctx.comparator().getText();
        /*true if comparator is one of: <, >, >=, <= and false if operator is == or !-)*/
        boolean compareOp = (boolean) visit(ctx.comparator());

        if (type1 != type2 || (compareOp && (type1 != Types.INT || varFlag1 != 0 || varFlag2 != 0))) {
            addError(ctx, "Incomparable types: operator %s cannot be applied to %s and %s", operator, type1, type2);
        } else {
            if (varFlag1 == varFlag2) {
                setType(ctx, Types.BOOL);
            } else if (varFlag1 > 0 && varFlag2 > 0) {
                addError(ctx, "Cannot compare arrays of different sizes: %s and %s", varFlag1, varFlag2);
            } else {
                addError(ctx, "Cannot compare array with single value");

            }
        }
        return 0;
    }

    /**
     * Visitor for compare operator
     *
     * @return true if comparator is one of: <, >, >=, <= and false if operator is == or !-)
     */
    @Override
    public Object visitComparator(MyLangParser.ComparatorContext ctx) {
        if (ctx.getText().equals("==") || ctx.getText().equals("!=")) {
            return false;
        }
        return true;
    }

    /**
     * Checker for when the RHS of the assignment of the expression is a array
     * arrLength != -1 should be checked before calling this method
     *
     * @param ctx        context used for adding errors
     * @param arrLength  RHS length
     * @param initLength LHS length
     * @param scope      name of the scope used for adding errors
     * @param varNameStr name of the array
     */
    private void checkAssArr(ParserRuleContext ctx, String varNameStr, int arrLength, String scope, int initLength) {
        if (arrLength == 0) {
            if (initLength != 1) {
                addError(ctx, "Cannot assign array to single value");
            }
        } else {
            if (initLength == 0) {
                addError(ctx, "Cannot declare array %s with length 0", varNameStr);
            }
            if (arrLength > initLength) {
                addError(ctx, "Value for array %s is too long", varNameStr, scope);
            } else if (arrLength < initLength) {
                addError(ctx, "Initializer for array %s is too short", varNameStr, scope);
            }
        }
    }

    /**
     * Checker for when the RHS of the assignment of the expression is a single value
     * varFlag != -1 should be checked before calling this method
     *
     * @param ctx     context used for adding errors
     * @param varFlag a flag that is: -1 for undefined value,
     *                0 for single value, >0 for array (representing length)
     */
    private void checkAssSingleVal(ParserRuleContext ctx, int varFlag) {
        if (varFlag != 0) {
            addError(ctx, "Cannot assign single value to array");
        }
    }

    /**
     * Visitor for constant value
     *
     * @return the varFlag that is passed by visiting the value
     */
    @Override
    public Object visitConstValue(MyLangParser.ConstValueContext ctx) {
        int val = (int) visit(ctx.value());
        setType(ctx, getType(ctx.value()));
        return val;
    }

    /**
     * Visitor for variable value
     *
     * @return the varFlag that is passed by visiting the variable
     */
    @Override
    public Object visitVariableValue(MyLangParser.VariableValueContext ctx) {
        int varVal = (int) visit(ctx.varValue());
        setType(ctx, getType(ctx.varValue()));
        return varVal;
    }

    /**
     * Visitor for number
     *
     * @return 0 (varFlag) because its a single value
     */
    @Override
    public Object visitNumber(MyLangParser.NumberContext ctx) {
        setType(ctx, Types.INT);
        return 0;
    }

    /**
     * Visitor for true value
     *
     * @return 0 (varFlag) because its a single value
     */
    @Override
    public Object visitTrueVal(MyLangParser.TrueValContext ctx) {
        setType(ctx, Types.BOOL);
        return 0;
    }

    /**
     * Visitor for false value
     *
     * @return 0 (varFlag) because its a single value
     */
    @Override
    public Object visitFalseVal(MyLangParser.FalseValContext ctx) {
        setType(ctx, Types.BOOL);
        return 0;
    }

    /**
     * Visitor for array value (RHS)
     * Checks if all values in array have same type
     *
     * @return length (varFlag) of the array
     */
    @Override
    public Object visitArrayVal(MyLangParser.ArrayValContext ctx) {
        visit(ctx.value(0));
        Types type = getType(ctx.value(0));
        int length = ctx.value().size();
        for (int i = 1; i < length; i++) {
            visit(ctx.value(i)); // if(visit(ctx.value(i)) != 0) addError() - in order to restrict nested arrays
            if (!checkTypeBool(ctx.value(i), type)) {
                addError(ctx, "All values in array should have same type");
                break;
            }
        }
        setType(ctx, type);
        return length;
    }

    /**
     * Visitor for single value (RHS)
     * The name "single value" may be a bit misleading as there can be
     * a usage of "a" where is an array: int a[2] = [1,2];
     *
     * @return length (varFlag) of the array
     */
    @Override
    public Object visitSingleVal(MyLangParser.SingleValContext ctx) {
        String varNameStr = ctx.ID().getText();
        if (sharedMemoryLocations.contains(varNameStr)) {
            addError(ctx, "Cannot use name of fork/lock");
        }
        Variable var = curThreadSymbolTable.getVar(varNameStr);
        if (var == null) {
            addError(ctx, "Variable %s was not declared in this scope", varNameStr);
            return -1;
        } else {
            if (!var.hasValue()) {
                setVar(ctx, curThreadSymbolTable.getVar(varNameStr));
                addWarning(ctx, "Variable %s was not initialized", varNameStr);
            }
            setVar(ctx, curThreadSymbolTable.getVar(varNameStr));
            /*checks if the declared variable is of single var subtype */
            boolean isSingleVar = SingleVar.class.isAssignableFrom(var.getClass());
            setType(ctx, var.getType());
            if (isSingleVar) {
                return 0;
            } else {
                ArrayVar varArr = (ArrayVar) var;
                return varArr.getLength();
            }
        }
    }

    /**
     * Visitor for array index value
     *
     * @return 0(varFlag) because we access one variable of the array so its a single value
     * or -1 if value is accessed is undefined
     */
    @Override
    public Object visitArrayIndexVal(MyLangParser.ArrayIndexValContext ctx) {
        visit(ctx.expression());
        String varNameStr = ctx.ID().getText();
        if (sharedMemoryLocations.contains(ctx.ID().getText())) {
            addError(ctx, "Cannot use name of fork/lock");
        }
        ArrayVar var = (ArrayVar) curThreadSymbolTable.getVar(varNameStr);
        if (var == null) {
            addError(ctx, "Variable %s was not declared in this scope", varNameStr);
            return -1;
        } else {
            if (!var.hasValue()) {
                setVar(ctx, curThreadSymbolTable.getVar(varNameStr));
                addWarning(ctx, "Variable %s was not initialized", varNameStr);
            }
            setVar(ctx, curThreadSymbolTable.getVar(varNameStr));
            if (ctx.expression() instanceof MyLangParser.ConstValueContext) {
                int index = Integer.parseInt(ctx.expression().getText());
                if (index > var.getLength() - 1) {
                    addError(ctx, "Cannot access array " + varNameStr + " at index " + index);
                    return -1;
                }
            }
            setType(ctx, var.getType());
            return 0;
        }
    }

    /**
     * Visitor for a single variable name (LHS)
     *
     * @return a string array with one value that is the id of the variable
     */
    @Override
    public Object visitSingle(MyLangParser.SingleContext ctx) {
        if (sharedMemoryLocations.contains(ctx.ID().getText())) {
            addError(ctx, "Cannot use name of fork/lock");
        }
        return new String[]{ctx.ID().getText()};
    }

    /**
     * Visitor for a single variable name (LHS) (e.g. int a[5]; array of length 5)
     *
     * @return a string array with two values: id of the array and length
     */
    @Override
    public Object visitArrayIndex(MyLangParser.ArrayIndexContext ctx) {
        if (sharedMemoryLocations.contains(ctx.ID().getText())) {
            addError(ctx, "Cannot use name of fork/lock");
        }
        return new String[]{ctx.ID().getText(), ctx.NUM().getText()};
    }

    /**
     * Helper method that formats and adds error messages
     *
     * @param ctx  the context where the error occurred
     * @param msg  the error string that needs to be formatted
     * @param args the values that are used to format the string
     */
    private void addError(ParserRuleContext ctx, String msg, Object... args) {
        Token token = ctx.getStart();
        String line = String.format("Error at line %d:%d -> ",
                token.getLine(), token.getCharPositionInLine());
        String errorMsg = String.format(msg, args);
        this.errors.add(line + errorMsg);
        setType(ctx, Types.ERROR);
    }

    /**
     * Helper method that formats and adds warning messages
     *
     * @param ctx  the context where the warning occurred
     * @param msg  the warning string that needs to be formatted
     * @param args the values that are used to format the string
     */
    private void addWarning(ParserRuleContext ctx, String msg, Object... args) {
        Token token = ctx.getStart();
        String line = String.format("Warning at line %d:%d -> ",
                token.getLine(), token.getCharPositionInLine());
        String warningMsg = String.format(msg, args);
        this.warnings.add(line + warningMsg);
    }

    /**
     * Setter for the node and its type
     */
    private void setType(ParseTree node, Types type) {
        this.resultTrees.setType(node, type);
    }

    /**
     * Getter for type of a node
     */
    private Types getType(ParseTree node) {
        return this.resultTrees.getType(node);
    }

    /**
     * Setter for the node and its variable
     */
    private void setVar(ParseTree node, Variable var) {
        this.resultTrees.setVar(node, var);
    }

    /**
     * Getter for variable of a node
     */

    private Variable getVar(ParseTree node) {
        return this.resultTrees.getVar(node);
    }

    /**
     * Checker for the type
     * Adds an error if types are not matching
     *
     * @param node     the node that needs to be checked
     * @param expected the expected type of the node
     * @throws IllegalArgumentException if node does not have a type
     */
    private void checkType(ParserRuleContext node, Types expected) {
        Types actual = getType(node);
        if (actual == null) {
            throw new IllegalArgumentException("Missing inferred type of "
                    + node.getText());
        }
        if (!actual.equals(expected)) {
            addError(node, "Expected type '%s' but found '%s'", expected,
                    actual);
        }
    }

    /**
     * Checker for the type
     * Adds an error if types are not matching
     *
     * @param node     the node that needs to be checked
     * @param expected the expected type of the node
     * @return true if checks is successful and false if types mismatch
     * @throws IllegalArgumentException if node does not have a type
     */
    private boolean checkTypeBool(ParserRuleContext node, Types expected) {
        Types actual = getType(node);
        if (actual == null) {
            throw new IllegalArgumentException("Missing inferred type of "
                    + node.getText());
        }
        if (!actual.equals(expected)) {
            addError(node, "Expected type '%s' but found '%s'", expected,
                    actual);
            return false;
        }
        return true;
    }

    /**
     * Getter for the symbol table
     */
    public SymbolTable getSymbolTable() {
        return this.curThreadSymbolTable;
    }
}
