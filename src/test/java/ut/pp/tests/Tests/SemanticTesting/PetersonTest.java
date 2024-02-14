package ut.pp.tests.Tests.SemanticTesting;

import org.junit.Assert;
import org.junit.Test;
import ut.pp.CompileMyLang;

import java.io.File;

public class PetersonTest {
    private CompileMyLang cmp = new CompileMyLang();
    private String file = "/peterson";

    @Test
    public void runPeterson(){
        File haskellFile = cmp.compileSequence(file);
        String actual = cmp.getOutput(cmp.runFile(haskellFile),true);
        Assert.assertNotEquals(actual, "");
    }
}
