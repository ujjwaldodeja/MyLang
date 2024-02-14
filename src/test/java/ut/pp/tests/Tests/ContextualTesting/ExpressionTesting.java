package ut.pp.tests.Tests.ContextualTesting;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import ut.pp.check.Checker;

import java.util.List;

import static ut.pp.CompileMyLang.instance;

public class ExpressionTesting {
    private Checker check = new Checker();

    @Test
    public void testReadOutOfBoundsAccess(){
        ParseTree tree = instance().parseInput("int x[5]; x[2] = x[6];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 3);
        Assert.assertEquals(errors.get(0), "Error at line 1:17 -> Cannot access array x at index 6");
        Assert.assertEquals(errors.get(1), "Error at line 1:17 -> Expected type 'INT' but found 'ERROR'");
        Assert.assertEquals(errors.get(2), "Error at line 1:10 -> Cannot assign to undefined expression");
    }

    @Test
    public void testWriteOutOfBoundsAccess(){
        ParseTree tree = instance().parseInput("int x[5]; x[5] = x[1];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals(errors.get(0), "Error at line 1:10 -> Index 5 out of bounds for array x");
    }

    @Test
    public void testAccessByExpression(){
        ParseTree tree = instance().parseInput("int x[5]; x[3] = x[5+2];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }
}
