package ut.pp.tests.Tests.SemanticTesting;

import org.junit.Assert;
import org.junit.Test;
import ut.pp.CompileMyLang;

import java.io.File;

public class WhileTesting {
    public CompileMyLang cmp = new CompileMyLang();
    public String file = "/testWhile";

    @Test
    public void runWhile(){
        File haskellFile = cmp.compileSequence(file);
        String actual = cmp.getOutput(cmp.runFile(haskellFile),true);
        String expected = "Sprockell 0 says 6\n" +
                "Sprockell 0 says 10\n" +
                "Sprockell 0 says 13\n" +
                "Sprockell 0 says 15\n" +
                "Sprockell 0 says 16\n" +
                "Sprockell 0 says 16\n" +
                "Sprockell 0 says 15\n" +
                "Sprockell 0 says 13\n";
        Assert.assertEquals(actual, expected);
    }
}
