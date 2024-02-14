package ut.pp.tests.Tests.SemanticTesting;

import org.junit.Assert;
import org.junit.Test;
import ut.pp.CompileMyLang;

import java.io.File;

public class PrimeTest {
    private CompileMyLang cmp = new CompileMyLang();
    private String file = "/prime";

    @Test
    public void runPrime(){
        File haskellFile = cmp.compileSequence(file);
        String actual = cmp.getOutput(cmp.runFile(haskellFile),true);
        String expected = "Sprockell 0 says 5\n";
        Assert.assertEquals(actual, expected);
    }
}
