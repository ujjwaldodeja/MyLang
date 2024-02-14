package ut.pp.tests.Tests.ContextualTesting;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Test;
import ut.pp.EDSL.Types;
import ut.pp.check.Checker;
import org.junit.Assert;
import ut.pp.check.Scope;
import ut.pp.check.SymbolTable;

import java.util.List;

import static ut.pp.CompileMyLang.instance;

public class AssignmentTesting {
    private Checker check = new Checker();
    private String DIR = "src/main/java/ut/pp/testFiles/";

    @Test
    public void testBoolAssignment(){
        ParseTree tree = instance().parseInput("bool a = true;\nbool d = a;");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    //to test valid assignment to bool array
    @Test
    public void testBoolArray(){
        ParseTree tree = instance().parseInput("bool a[4] = [true,false,false,true];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    //tests another edge case for the assignment to a bool array
    @Test
    public void testBoolArray2(){
        ParseTree tree = instance().parseInput("bool stop = true;\nbool move = false;\nbool a[2];\na[0] = stop;\na[1] = move;");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    //tests another edge case for the assignment to a bool array
    @Test
    public void testBoolArray3(){
        instance().parseInput("bool stop = true;\nbool move = false;\nbool a[2] = [stop, move];");
        List<String> errors = instance().getErrors();
        Assert.assertEquals(errors.size(), 3);
        Assert.assertEquals(errors.get(0), "line 3:13 mismatched input 'stop' expecting {'true', 'false', NUM, '-', '['}");
    }

    //for testing invalid type assignment to bool array
    @Test
    public void testInvalidBoolArray(){
        ParseTree tree = instance().parseInput("bool a[4] = [true,false,false,1];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 2);
        Assert.assertEquals("Error at line 1:30 -> Expected type 'BOOL' but found 'INT'", errors.get(0));
        Assert.assertEquals("Error at line 1:12 -> All values in array should have same type", errors.get(1));
    }

    //for testing correct assignment to int array
    @Test
    public void testIntArray(){
        ParseTree tree = instance().parseInput("int a[4] = [1,2,3,4];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void testIntArray2(){
        ParseTree tree = instance().parseInput("int x = 1;\nint y = 2;\nint a[2];\na[0] = x;\na[1] = y;");
        check.check(tree);
        List<String> errors = check.getErrors();
        for(String error : errors){
            System.out.print(error);
        }
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void testIntArray3(){
        ParseTree tree = instance().parseInput("int x = 1;\nint y = 2;\nint a[2] = [x,y];");
        List<String> errors = instance().getErrors();
        Assert.assertEquals(errors.size(), 3);
        Assert.assertEquals(errors.get(0), "line 3:12 mismatched input 'x' expecting {'true', 'false', NUM, '-', '['}");
    }

    //for testing incorrect type assignment to int array
    @Test
    public void testInvalidIntArray(){
        ParseTree tree = instance().parseInput("int a[4] = [1,2,3,true];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 2);
        Assert.assertEquals("Error at line 1:18 -> Expected type 'INT' but found 'BOOL'", errors.get(0));
        Assert.assertEquals("Error at line 1:11 -> All values in array should have same type", errors.get(1));
    }

    @Test
    public void testInvalidType(){
        ParseTree tree = instance().parseInput("int a = 1;" + "\nbool b = a;");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals("Error at line 2:9 -> Expected type 'BOOL' but found 'INT'", errors.get(0));
    }

    @Test
    public void testType(){
        ParseTree tree = instance().parseInput("int a = 1;" + "\nint b = a;");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void scopeTesting(){
        ParseTree tree = instance().parseFile(DIR + "/testAssignment");
        check.check(tree);
        SymbolTable table = check.getSymbolTable();
        Scope mainScopes = table.getMainScope();
        Assert.assertEquals(mainScopes.getName(),"global");
        Assert.assertEquals(mainScopes.getVar("a").getType(), Types.BOOL);
        Assert.assertEquals(mainScopes.getVar("n").getType(), Types.INT);
        Assert.assertEquals(mainScopes.getVar("m").getType(), Types.BOOL);
        Assert.assertEquals(mainScopes.getVar("x").getType(), Types.INT);
        Assert.assertEquals(mainScopes.getVar("r").getType(), Types.BOOL);

    }

    @Test
    public void testTooShortAssignment() {
        ParseTree tree = instance().parseInput("int x[5] = [1,2,3,4];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals("Error at line 1:0 -> Initializer for array x is too short", errors.get(0));
    }

    @Test
    public void testTooLongAssignment() {
        ParseTree tree = instance().parseInput("int x[5] = [1,2,3,4,5,6];");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals("Error at line 1:0 -> Value for array x is too long", errors.get(0));
    }

    @Test
    public void testAssignmentAfterDecl() {
        ParseTree tree = instance().parseInput("int x; x = 5;");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }
}
