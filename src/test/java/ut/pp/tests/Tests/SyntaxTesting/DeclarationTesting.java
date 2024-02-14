package ut.pp.tests.Tests.SyntaxTesting;
//function

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ut.pp.check.Checker;

import java.util.List;

import static ut.pp.CompileMyLang.instance;

public class DeclarationTesting {
    private Checker check;
    private final static String PATH = "src/main/java/ut/pp/testFiles";

    @Before
    public void initialise(){
        check = new Checker();
    }

    //checks correct declaration for integer array
    @Test
    public void testIntArray(){
        ParseTree tree = instance().parseInput("int a[4] = [1,2,3,4];");
        check.check(tree);
        List<String> errors = check.getErrors();
        for(String error : errors){
            System.out.print(error);
        }
        Assert.assertEquals(errors.size(), 0);
    }

    //catches error for absent terminator (;)
    @Test
    public void testBoolArray2(){
        ParseTree tree = instance().parseInput("bool a[4] = [true,false,false,true]");
        List<String> errors = instance().getErrors();
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals("line 1:35 missing ';' at '<EOF>'", errors.get(0));
    }

    //checks for correct declaration of boolean array
    @Test
    public void testBoolArray(){
        ParseTree tree = instance().parseInput("bool a[4] = [true,false,false,true];");
        check.check(tree);
        List<String> errors = check.getErrors();
        for(String error : errors){
            System.out.print(error);
        }
        Assert.assertEquals(errors.size(), 0);
    }
}
