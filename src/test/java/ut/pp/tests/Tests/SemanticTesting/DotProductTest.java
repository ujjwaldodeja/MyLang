package ut.pp.tests.Tests.SemanticTesting;

import org.junit.Assert;
import org.junit.Test;
import ut.pp.CompileMyLang;

import java.io.File;

public class DotProductTest {
    private CompileMyLang cmp = new CompileMyLang();
    private String file = "/dotProduct";

    @Test
    public void runBanking() {
        File haskellFile = cmp.compileSequence(file);
        System.out.println(haskellFile);
        String actual = cmp.getOutput(cmp.runFile(haskellFile), true);
        String expected = "Sprockell 0 says 3\n";
        Assert.assertEquals(actual, expected);
    }
}
