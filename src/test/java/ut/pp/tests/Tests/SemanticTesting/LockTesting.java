package ut.pp.tests.Tests.SemanticTesting;

import org.junit.Assert;
import org.junit.Test;
import ut.pp.CompileMyLang;

import java.io.File;

public class LockTesting {
    private CompileMyLang cmp = new CompileMyLang();
    private String file = "/testLock";

    @Test
    public void runLock(){
        File haskellFile = cmp.compileSequence(file);
        System.out.println(haskellFile);
        String actual = cmp.getOutput(cmp.runFile(haskellFile),true);
        String expected = "Sprockell 0 says 0\n";
        Assert.assertEquals(actual, expected);
    }
}
