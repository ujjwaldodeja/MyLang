package ut.pp.check;

import org.antlr.v4.runtime.ParserRuleContext;
import org.antlr.v4.runtime.tree.ParseTree;
import org.antlr.v4.runtime.tree.ParseTreeProperty;
import ut.pp.EDSL.Types;
import ut.pp.EDSL.Variable;

public class ResultTrees {
    /** Tree that contains variable objects for a given context*/
    private final ParseTreeProperty<Variable> vars = new ParseTreeProperty<>();
    /** Tree that contains a type for every node of the parse tree*/
    private final ParseTreeProperty<Types> types = new ParseTreeProperty<>();

    public ParseTreeProperty<Variable> getVars() {
        return vars;
    }

    public Variable getVar(ParseTree node) {
        return this.vars.get(node);
    }


    public void setVar(ParseTree node, Variable var) {
        this.vars.put(node, var);
    }

    public Types getType(ParseTree node) {
        return this.types.get(node);
    }

    public void setType(ParseTree node, Types type) {
        this.types.put(node, type);
    }

}
