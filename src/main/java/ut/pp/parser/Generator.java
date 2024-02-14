package ut.pp.parser;

import main.antlr4.ut.pp.parser.MyLangBaseVisitor;
import main.antlr4.ut.pp.parser.MyLangParser;
import org.antlr.v4.runtime.tree.ParseTree;
import ut.pp.EDSL.*;
import ut.pp.SPRIL.Rel;
import ut.pp.check.ResultTrees;
import ut.pp.check.SymbolTable;

import java.lang.reflect.Array;
import java.util.*;
import java.util.concurrent.locks.Lock;

/**
 * Class Generator implements a visitor that adds all statements and threads it encounters to the program
 */
public class Generator extends MyLangBaseVisitor {
    private Program prog;
    private ResultTrees parseTreeVarInfo;

    /**
     * Run the visitor to construct the EDSL
     * @param tree ParseTree from ANTLR
     * @return Program
     */
    public Program assemble(ParseTree tree, ResultTrees parseTreeVarInfo) {
        this.parseTreeVarInfo = parseTreeVarInfo;
        tree.accept(this);
        return prog;
    }

    /**
     * Visit the program node of the parsetree
     * Adds all child statements to the program
     * @param ctx the current node
     * @return the Program EDSL object
     */
    @Override
    public Program visitProgram(MyLangParser.ProgramContext ctx) {
        prog = new Program();
        List<Statement> statements = (List<Statement>) visit(ctx.sharedVars());
        statements.addAll((List<Statement>) visit(ctx.statementList()));
        for (Statement st : statements) {
            prog.addStatement(st);
        }
        return prog;
    }

    /**
     * Visit the sharedVars node of the parse tree
     * Creates a list of all the shared variable declarations
     * @param ctx the parse tree
     * @return all statements (declarations) in the sharedVars node
     */
    @Override
    public Object visitSharedVars(MyLangParser.SharedVarsContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (MyLangParser.SharedDeclarationContext context : ctx.sharedDeclaration()) {
            Statement st = (Statement) visit(context);
            statements.add(st);
        }
        return statements;
    }

    /**
     * Visit the sharedDeclaration node of the parse tree
     * Creates a Declaration object
     * @param ctx the parse tree
     * @return declaration
     */
    @Override
    public Object visitSharedDeclaration(MyLangParser.SharedDeclarationContext ctx) {
        List<Variable> vars = new ArrayList<>();
        for (MyLangParser.VarNameContext varName: ctx.varName()) {
            vars.add((Variable) visit(varName));
        }
        Declaration decl = new Declaration(vars);
        return decl;
    }

    /**
     * Visit a statementList node of the parsetree
     * Creates a list of all statements in children of the node
     * @param ctx the current node
     * @return all statements in the statementList node
     */
    @Override
    public Object visitStatementList(MyLangParser.StatementListContext ctx) {
        List<Statement> statements = new ArrayList<>();

        for (MyLangParser.StatementContext context : ctx.statement()) {
            Statement st = (Statement) visit(context);
            statements.add(st);
        }
        return statements;
    }

    /**
     * Visit a declaration node of the parsetree
     * Creates a declaration object
     * @param ctx the current node
     * @return declaration
     */
    @Override
    public Object visitDeclaration(MyLangParser.DeclarationContext ctx) {
        List<Variable> vars = new ArrayList<>();
        for (MyLangParser.VarNameContext varName: ctx.varName()) {
            vars.add((Variable) visit(varName));
        }
        Declaration decl = new Declaration(vars);
        return decl;
    }

    /**
     * Visit a declarationAndAssignment node of the parsetree
     * This creates an assignment object containing the variable and the expression
     * @param ctx the parse tree
     * @return assignment
     */
    @Override
    public Object visitDeclarationAndAssignment(MyLangParser.DeclarationAndAssignmentContext ctx) {
        Variable var = (Variable) visit(ctx.varName());
        Expression expr = (Expression) visit(ctx.expression());
        Assignment ass = new Assignment(var, expr);
        return ass;
    }

    /**
     * Visit an assignment node of the parsetree
     * This creates an assignment object containing the variable and the expression
     * @param ctx the parse tree
     * @return assignment
     */
    @Override
    public Object visitAssignment(MyLangParser.AssignmentContext ctx) {
        Variable var = (Variable) visit(ctx.varName());
        Expression expr = (Expression) visit(ctx.expression());
        Assignment ass = new Assignment(var, expr);
        return ass;
    }

    /**
     * Visit an if clause node of the parsetree
     * This creates an ifStatement object containing the conditions and statements of the if and else clauses
     * @param ctx the parse tree
     * @return ifStatement
     */
    @Override
    public Object visitIfClause(MyLangParser.IfClauseContext ctx) {
        List<Expression> conditions = new ArrayList<>();
        List<List<Statement>> statementLists = new ArrayList<>();

        for (MyLangParser.ExpressionContext context : ctx.expression()) {
            conditions.add((Expression) visit(context));
        }
        for (MyLangParser.StatementListContext context : ctx.statementList()) {
            statementLists.add((List<Statement>) visit(context));
        }

        IfStatement ifSt = new IfStatement(conditions, statementLists);
        return ifSt;
    }

    /**
     * Visit the while clause node of the parse tree
     * this creates a whileStatement object containing the condition and the statements of the while clause
     * @param ctx the parse tree
     * @return ifStatement
     */
    @Override
    public Object visitWhileClause(MyLangParser.WhileClauseContext ctx) {
        Expression condition = (Expression) visit(ctx.expression());
        List<Statement> statements = (List<Statement>) visit(ctx.statementList());

        WhileStatement whileSt = new WhileStatement(condition, statements);
        return whileSt;
    }

    /**
     * Visit a fork node of the parse tree
     * This creates a ForkStatement object containing the threadName and the statements of the fork
     * @param ctx the parse tree
     * @return forkStatement
     */
    @Override
    public Object visitFork(MyLangParser.ForkContext ctx) {
        ForkVar var = (ForkVar) parseTreeVarInfo.getVar(ctx);
        List<Statement> statements = (List<Statement>) visit(ctx.statementList());

        ForkStatement forkSt = new ForkStatement(var, statements);
        prog.addThread(var, statements);

        return forkSt;
    }

    /**
     * Visit a Print node of the parse tree
     * This creates a FunctionCall object with name Function.PRINT, containing the expression to be printed
     * @param ctx the parse tree
     * @return functionCall for print
     */
    @Override
    public Object visitPrint(MyLangParser.PrintContext ctx) {
        FunctionCall.Function funcName = FunctionCall.Function.PRINT;
        Expression arg = (Expression) visit(ctx.expression());
        return new FunctionCall(funcName, List.of(arg));
    }

    /**
     * Visit a getLock node of the parse tree
     * This creates a GetLock object with the name of the lock
     * @param ctx the parse tree
     * @return getLock
     */
    @Override
    public Object visitGetLock(MyLangParser.GetLockContext ctx) {
        LockVar var = (LockVar) parseTreeVarInfo.getVar(ctx);
        return new GetLock(var);
    }

    /**
     * Visit a releaseLock node of the parse tree
     * This creates a ReleaseLock object with the name of the lock
     * @param ctx the parse tree
     * @return releaseLock
     */
    @Override
    public Object visitReleaseLock(MyLangParser.ReleaseLockContext ctx) {
        LockVar var = (LockVar) parseTreeVarInfo.getVar(ctx);
        return new ReleaseLock(var);
    }

    /**
     * Visit a Join node of the parse tree
     * This creates a Join object with the name of the thread
     * @param ctx the parse tree
     * @return join
     */
    @Override
    public Object visitJoin(MyLangParser.JoinContext ctx) {
        ForkVar var = (ForkVar) parseTreeVarInfo.getVar(ctx);
        return new Join(var);
    }

    /**
     * visit a PrefixOp node of the parse tree
     * This creates an Operation object with the operator and the expression
     * @param ctx the parse tree
     * @return operation
     */
    @Override
    public Object visitPrefixOp(MyLangParser.PrefixOpContext ctx) {
        Expression expr = (Expression) visit(ctx.expression());
        Operation.Operator operator;

        MyLangParser.PrefixOperatorContext operatorContext = ctx.prefixOperator();
        if (operatorContext.NOT() != null) {
            operator = Operation.Operator.NOT;
        }
        else { // (operatorContext.MINUS() != null)
            operator = Operation.Operator.NEG;
        }

        return new Operation(expr, null, operator);
    }

    /**
     * Visit a Multiplication node in the parse tree
     * This creates an Operation object with the expressions and the operator
     * @param ctx the parse tree
     * @return operation
     */
    @Override
    public Object visitMultiplication(MyLangParser.MultiplicationContext ctx) {
        Expression expr1 = (Expression) visit(ctx.expression(0));
        Expression expr2 = (Expression) visit(ctx.expression(1));
        Operation.Operator operator;

        MyLangParser.MultOperatorContext operatorContext = ctx.multOperator();
        if (operatorContext.STAR() != null) {
            operator = Operation.Operator.STAR;
        }
        else {
            operator = Operation.Operator.DIV;
        }

        return new Operation(expr1, expr2, operator);
    }

    /**
     * Visit an Addition node in the parse tree
     * This creates an Operation object with the expressions and the operator
     * @param ctx the parse tree
     * @return operation
     */
    @Override
    public Object visitAddition(MyLangParser.AdditionContext ctx) {
        Expression expr1 = (Expression) visit(ctx.expression(0));
        Expression expr2 = (Expression) visit(ctx.expression(1));
        Operation.Operator operator;

        MyLangParser.AddOperatorContext operatorContext = ctx.addOperator();
        if (operatorContext.PLUS() != null) {
            operator = Operation.Operator.PLUS;
        }
        else { // (operatorContext.MINUS != null)
            operator = Operation.Operator.MINUS;
        }

        return new Operation(expr1, expr2, operator);
    }

    /**
     * Visit a Comparison node in the parse tree
     * This creates an Operation object with the expressions and the operator
     * @param ctx the parse tree
     * @return operation
     */
    @Override
    public Object visitComparison(MyLangParser.ComparisonContext ctx) {
        Expression expr1 = (Expression) visit(ctx.expression(0));
        Expression expr2 = (Expression) visit(ctx.expression(1));
        Operation.Operator operator;

        MyLangParser.ComparatorContext operatorContext = ctx.comparator();
        if (operatorContext.EQUALS() != null) {
            operator = Operation.Operator.EQUALS;
        }
        else if (operatorContext.SMALLER() != null) {
            operator = Operation.Operator.SMALLER;
        }
        else if (operatorContext.SMALLEREQUALS() != null) {
            operator = Operation.Operator.SMALLEREQUALS;
        }
        else if (operatorContext.BIGGER() != null) {
            operator = Operation.Operator.BIGGER;
        }
        else if (operatorContext.BIGGEREQUALS() != null) {
            operator = Operation.Operator.BIGGEREQUALS;
        }
        else { // (operatorContext.NOTEQUAL != null)
            operator = Operation.Operator.NOTEQUAL;
        }

        return new Operation(expr1, expr2, operator);
    }

    /**
     * Visit a BoolOp node of the parse tree
     * This creates an Operation object with the expressions and the operator
     * @param ctx the parse tree
     * @return operation
     */
    @Override
    public Object visitBoolOp(MyLangParser.BoolOpContext ctx) {
        Expression expr1 = (Expression) visit(ctx.expression(0));
        Expression expr2 = (Expression) visit(ctx.expression(1));
        Operation.Operator operator;

        MyLangParser.BoolOperatorContext operatorContext = ctx.boolOperator();
        if (operatorContext.AND() != null) {
            operator = Operation.Operator.AND;
        }
        else { // (operatorContext.OR != null)
            operator = Operation.Operator.OR;
        }

        return new Operation(expr1, expr2, operator);
    }

    /**
     * Visit a Brackets node in the parse tree
     * This returns the return value of the child expression node
     * @param ctx the parse tree
     * @return Expression: return value of the child expression
     */
    @Override
    public Object visitBrackets(MyLangParser.BracketsContext ctx) {
        return visit(ctx.expression());
    }

    /**
     * Visit a ConstValue node in the parse tree
     * This returns the return value of the child value node
     * @param ctx the parse tree
     * @return LoadConst: return value of the child value node
     */
    @Override
    public Object visitConstValue(MyLangParser.ConstValueContext ctx) {
        return visit(ctx.value());
    }

    /**
     * Visit a VariableValue node in the parse tree
     * This returns a LoadVar object with the variable
     * @param ctx the parse tree
     * @return loadVar
     */
    @Override
    public Object visitVariableValue(MyLangParser.VariableValueContext ctx) {
        Variable var = (Variable) visit(ctx.varValue());
        if (var instanceof SingleVar) {
            return new LoadVar((SingleVar) var);
        }
        else {
            return new LoadArrayVar((ArrayVar) var);
        }
    }

    /**
     * Visit a Number node in the parse tree
     * This creates a LoadConst object with the value of the number
     * @param ctx the parse tree
     * @return loadConst
     */
    @Override
    public Object visitNumber(MyLangParser.NumberContext ctx) {
        String valueStr = ctx.NUM().getText();
        int value = Integer.parseInt(valueStr);
        if (ctx.MINUS() != null) value = -value;
        return new LoadConst(value);
    }

    /**
     * Visit a TrueVal node of the parse tree
     * This creates a LoadConst object with value 1
     * @param ctx the parse tree
     * @return loadConst
     */
    @Override
    public Object visitTrueVal(MyLangParser.TrueValContext ctx) {
        return new LoadConst(1);
    }

    /**
     * Visit a FalseVal node of the parse tree
     * This creates a LoadConst object with value 0
     * @param ctx the parse tree
     * @return loadConst
     */
    @Override
    public Object visitFalseVal(MyLangParser.FalseValContext ctx) {
        return new LoadConst(0);
    }

    /**
     * Visit an ArrayVal node of the parse tree
     * This creates a LoadArray object with the values of the array
     * @param ctx the parse tree
     * @return loadArray
     */
    @Override
    public Object visitArrayVal(MyLangParser.ArrayValContext ctx) {
        List<LoadConst> values = new ArrayList<>();
        for (MyLangParser.ValueContext context : ctx.value()) {
            values.add((LoadConst) visit(context));
        }
        return new LoadArrayValues(values);
    }

    /**
     * Visit a Single node of the parse tree
     * This creates a SingleVar object representing the lhs variable in case the variable
     * is not an array
     * If the variable is an array, then we must be writing the content of an entire array
     * to the variable (because no index is given) and we return the ArrayVar
     * @param ctx the parse tree
     * @return lhs variable
     */
    @Override
    public Object visitSingle(MyLangParser.SingleContext ctx) {
        Variable var = parseTreeVarInfo.getVar(ctx);
        return var;
    }

    /**
     * Visit an ArrayIndex node of the parse tree
     * This creates an ArrayVar object representing the lhs variable (an entire array)
     * in case of a declaration to a new array
     * It creates a SingleVar object representing the lhs variable at the given index
     * of the array in case of an assignment to an existing array
     * @param ctx the parse tree
     * @return lhs variable
     */
    @Override
    public Object visitArrayIndex(MyLangParser.ArrayIndexContext ctx) {
        ArrayVar var = (ArrayVar) parseTreeVarInfo.getVar(ctx);
        if (ctx.parent instanceof MyLangParser.AssignmentContext) {
            int arrayIndex = Integer.parseInt(ctx.NUM().getText());
            int indexMemoryAddress = var.getAddr() + arrayIndex;
            Variable indexVar = new SingleVar(var.getId(), var.getType(), var.hasValue(), indexMemoryAddress);
            return indexVar;
        }
        return var;
    }

    /**
     * Visit a SingleVar node of the parse tree
     * This creates a SingleVar object representing the rhs variable
     * @param ctx the parse tree
     * @return singleVar
     */
    @Override
    public Object visitSingleVal(MyLangParser.SingleValContext ctx) {
        Variable var = parseTreeVarInfo.getVar(ctx);
        return var;
    }

    /**
     * Visit an ArrayIndexVal node of the parse tree
     * This creates a SingleVar object representing the single index of the array that is being accessed
     * @param ctx the parse tree
     * @return singleVar: variable at array index
     */
    @Override
    public Object visitArrayIndexVal(MyLangParser.ArrayIndexValContext ctx) {
        Expression arrayIndex = (Expression) visit(ctx.expression());
        ArrayVar arrayVar = (ArrayVar) parseTreeVarInfo.getVar(ctx);
        int maxArrayIndex = arrayVar.getLength()-1;
        Variable indexVar = new SingleVar(arrayVar.getId(), arrayVar.getType(), arrayVar.hasValue(), arrayVar.getAddr(), arrayIndex, maxArrayIndex);
        return indexVar;
    }
}


