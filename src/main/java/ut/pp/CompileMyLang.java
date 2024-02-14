package ut.pp;

import main.antlr4.ut.pp.parser.MyLangLexer;
import main.antlr4.ut.pp.parser.MyLangParser;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.CharStreams;
import org.antlr.v4.runtime.CommonTokenStream;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.tree.ParseTree;
import ut.pp.EDSL.Program;
import ut.pp.SPRIL.Instruction;
import ut.pp.check.Checker;
import ut.pp.check.MyErrorListener;
import ut.pp.check.ResultTrees;
import ut.pp.check.SymbolTable;
import ut.pp.parser.Finalizer;
import ut.pp.parser.Generator;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CompileMyLang {
    private final static String PATH = "src/main/java/ut/pp/";
    private final static CompileMyLang instance = new CompileMyLang();
    private List<String> errors;
    private List<String> warnings;

    public static CompileMyLang instance() {
        return instance;
    }


    public ParseTree parseFile(String filePath) {
        try {
            return parse(CharStreams.fromFileName(filePath));
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }

    public ParseTree parseInput(String input) {
        return parse(CharStreams.fromString(input));
    }

    private ParseTree parse(CharStream input) {
        errors = new ArrayList<>();

        Lexer lexer = new MyLangLexer(input);
        lexer.removeErrorListeners();
        lexer.addErrorListener(new MyErrorListener(errors));
        MyLangParser parser = new MyLangParser(new CommonTokenStream(lexer));
        parser.removeErrorListeners();
        parser.addErrorListener(new MyErrorListener(errors));
        ParseTree tree = parser.program();

        if (!errors.isEmpty()) return null;

        return tree;
    }

    public File createHaskellFile(String instr, int numberOfThreads){
        File prog = new File(PATH + "prog.hs");
        try {
            FileWriter writer = new FileWriter(prog);
            writer.write("module Main where\n\n");
            writer.write("import Sprockell\n\n");
            writer.write("prog :: [Instruction]\n");
            writer.write("prog = " +  instr + '\n');
            writer.write("\nmain = run [prog");
            for (int i = 1; i < numberOfThreads; i++) writer.write(",prog");
            writer.write("]");
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return prog;
    }

    public String getOutput(Process process, boolean printToConsole) {
        BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
        String output = "";
        try {
            String line = reader.readLine();
            while (line != null) {
                if (printToConsole) System.out.println(line);
                output = output + line + "\n";
                line = reader.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return output;
    }

    public Process runFile(File file) {
        boolean isWindows = System.getProperty("os.name").toLowerCase().startsWith("windows");
        String command = "stack runhaskell " + file.getName();
        Process process = null;
        try {
            System.out.print('\n' + PATH + file.getName() + '\n');
            ProcessBuilder pb = new ProcessBuilder();
            pb.directory(new File(PATH));

            if(isWindows){
                pb.command("cmd.exe", "/c", command);
            } else {
                pb.command("sh", "-c", command);
            }
            process = pb.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return process;
    }

    public void printErrors() {
        for (String e : errors){
            System.out.println(e);
        }
    }

    public File compileSequence(String file){
        Checker check = new Checker();
        Generator generator = new Generator();
        String filePath = PATH + "testFiles/" + file;
        System.out.println(filePath);

        // Parse file
        ParseTree tree = parseFile(filePath);
        if (tree == null) {
            printErrors();
            return null;
        }

        // run checker
        ResultTrees resultTrees = check.check(tree);
        errors = check.getErrors();
        warnings = check.getWarnings();
        if (!warnings.isEmpty()) {
            for (String w: warnings) {
                System.out.println(w);
            }
        }

        if (!errors.isEmpty()) {
            printErrors();
            return null;
        }
        else {
            SymbolTable symbolTable = check.getSymbolTable();
            System.out.println(symbolTable);

            // Generate EDSL
            Program prog = generator.assemble(tree, resultTrees);

            // Generate SPRIL
            List<Instruction> instructionList = prog.toSpril();
            Finalizer.finalizeCode(instructionList);

            // Convert to String
            String progCode = Finalizer.toProgramCode(instructionList);
            System.out.print(Finalizer.prettyPrint(instructionList));

            // Convert to haskell and run
            File haskellFile = createHaskellFile(progCode, prog.getNumberOfThreads());
            instance().getOutput(instance().runFile(haskellFile), true);
            return haskellFile;
        }
    }

    public static void main(String[] args) {
        String file = "fib";
        instance().compileSequence(file);
    }

    public List<String> getErrors() {
        return errors;
    }
}
