package ut.pp.tests.Tests.SemanticTesting;

import org.junit.Assert;
import org.junit.Test;
import ut.pp.CompileMyLang;

import java.io.File;

public class FibTest {
    private CompileMyLang cmp = new CompileMyLang();
    private String file = "/fib";

    @Test
    public void runFib(){
        File haskellFile = cmp.compileSequence(file);
        System.out.println(haskellFile);
        String actual = cmp.getOutput(cmp.runFile(haskellFile), true);
        String expected = "Sprockell 0 says 2\n" +
                "Sprockell 0 says 3\n" +
                "Sprockell 0 says 5\n" +
                "Sprockell 0 says 8\n" +
                "Sprockell 0 says 13\n" +
                "Sprockell 0 says 13\n";
        Assert.assertEquals(actual, expected);
    }



}
