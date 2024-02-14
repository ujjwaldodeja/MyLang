package ut.pp.tests.Tests.ContextualTesting;

import org.antlr.v4.runtime.tree.ParseTree;
import org.junit.Assert;
import org.junit.Test;
import ut.pp.check.Checker;
import ut.pp.check.SymbolTable;

import java.util.List;

import static ut.pp.CompileMyLang.instance;

public class DeclarationTesting {
    private Checker check = new Checker();
    private String DIR = "src/main/java/ut/pp/testFiles/";
    private SymbolTable table;

    //checks if x and y were declared at the end of the if expression
    @Test
    public void testIfScope(){
            ParseTree tree = instance().parseInput("int y = 3;\n" + "int x;\n" + "bool stop = false;\n" + "bool next = true;\n" +
                    "\n" +
                    "if (stop) {\n" +
                    "    x = 2;\n" +
                    "    if (next) {\n" +
                    "        x = 5;\n" +
                    "    }\n" +
                    "    else {\n" +
                    "        x = 9;\n" +
                    "    }\n" +
                    "    y = 4;\n" +
                    "}\n" +
                    "elseif (stop) {\n" +
                    "    x = 6;\n" +
                    "}\n" +
                    "else {\n" +
                    "    x = 7;\n" +
                    "}\nprint(x);\nprint(y);");
        check.check(tree);
        List<String> errors = check.getErrors();
            Assert.assertEquals(errors.size(), 0);
    }

    //checks if the type of expression is indeed a bool
    @Test
    public void testIfExpression(){
        ParseTree tree = instance().parseInput("int x = 0;\nif (1) {\n" +
                "\tx = 5;" +
                "\n}" +
                "else {"+
                "\n\tx = 9;" +
                "\n}");
        check.check(tree);
        List<String> errors = check.getErrors();
        for(String error : errors){
            System.out.print(error);
        }
        Assert.assertEquals(errors.size(), 1);
        Assert.assertEquals("Error at line 2:4 -> Expected type 'BOOL' but found 'INT'",errors.get(0));

    }

    //Check redeclaration inside new scope
    @Test
    public void testIfScopeDeclaration(){
        ParseTree tree = instance().parseInput("int x = 0;\nif (true) {\n" +
            "\tint x = 5;" +
        "\n}" +
        "else {"+
            "\n\tint x = 9;" +
        "\n}");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void testDeclaration(){
        ParseTree tree = instance().parseInput("int a = 1;\nprint(a+1);");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void testNoDeclaration(){
        ParseTree tree = instance().parseInput("print(x);");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 2);
        Assert.assertEquals( "Error at line 1:6 -> Variable x was not declared in this scope",errors.get(0));
        Assert.assertEquals( "Error at line 1:0 -> Cannot print an undefined expression",errors.get(1));
    }

    @Test
    public void testWhileScopeDeclaration(){
        ParseTree tree = instance().parseInput("int x;\n" +
                "int n = 0;\n" +
                "while (n > 0){\n" +
                "    if (false){\n" +
                "        x = 0;\n" +
                "    }\n" +
                "    x = 9;\n" +
                "\n" +
                "}");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void testWhileDeclaration(){
        ParseTree tree = instance().parseInput("int x;\n" +
                "int n = 0;\n" +
                "while (n > 0){\n" +
                "    if (false){\n" +
                "        int x = 0;\n" +
                "    }\n" +
                "    int x = 9;\n" +
                "\n" +
                "}");
        check.check(tree);
        List<String> errors = check.getErrors();
        Assert.assertEquals(errors.size(), 0);
    }

    @Test
    public void testNoValueWarning() {
        ParseTree tree = instance().parseInput("" +
                "int x;" +
                "print(x);");
        check.check(tree);
        List<String> errors = check.getErrors();
        List<String> warnings = check.getWarnings();
        Assert.assertEquals(0, errors.size());
        Assert.assertEquals(1, warnings.size());
        Assert.assertEquals(warnings.get(0), "Warning at line 1:12 -> Variable x was not initialized");
    }

}
