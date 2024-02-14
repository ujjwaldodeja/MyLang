package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Class Program holds all necessary information to compile the program to the intermediate representation of spril code
 * A program has the format [Statements]
 */
public class Program {
    private final boolean ERROR_HANDLING = true; // Do we print fancy error messages, or optimize machine code length and exit program quietly

    private List<Statement> statements;
    private Map<ForkVar, List<Statement>> threads;


    /**
     * Program constructor
     * Constructs a program without any information about statements or threads, this be added while the
     * parser.Generator treeVisitor is running
     */
    public Program() {
        statements = new ArrayList<>();
        threads = new HashMap<>();
    }

    /**
     * Add a statement to the program
     * @param st statement
     */
    public void addStatement(Statement st) {
        statements.add(st);
    }

    /**
     * Add a thread to the program with all statements inside the fork body
     * @param thread     identifier of the thread
     * @param statements statements
     */
    public void addThread(ForkVar thread, List<Statement> statements) {
        threads.put(thread, statements);
    }

    /**
     * Get the number of threads in the program, including the main thread
     * @return number of threads
     */
    public int getNumberOfThreads() {
        return threads.size()+1;
    }

    /**
     * Generate Spril code for the idle loop. Here threads repeatedly read from their allocated shared memory address,
     * and when a non-zero value is written to this location, the thread jumps to the memory address with that value
     * and starts executing the code.
     * @return spril instruction list
     */
    private List<Instruction> generateThreadIdleLoop() {
        List<Instruction> instructions = new ArrayList<>();

        Instruction instrAfterSprockelIdLookup = new Instruction(OpCode.NOP);

        // Assign each sprockel id a location in memory
        int nextAssignedSprockelId = 1;
        for (ForkVar threadVar : threads.keySet()) {
            // Load the next sprockel Id
            instructions.add(new Instruction("Get thread memory location for thread "+nextAssignedSprockelId,
                    OpCode.LOAD, new ImmValue(nextAssignedSprockelId), new RegAddr("regA")));
            // if the thread matches the id, load the threadVar memory location into regA
            instructions.add(new Instruction(OpCode.COMPUTE, new Operator("NEq"), new RegAddr("regA"), new RegAddr("regSprID"), new RegAddr("regA")));
            instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regA"), new Rel(3)));
            instructions.add(new Instruction(OpCode.LOAD, new ImmValue(threadVar.getAddr()), new RegAddr("regA")));
            // Branch past checking other sprockel id's
            instructions.add(new Instruction(OpCode.JUMP, new TargetPtr(instrAfterSprockelIdLookup)));

            nextAssignedSprockelId++;
        }
        instructions.add(instrAfterSprockelIdLookup);

        // Read shared memory location where your next jump location will be set
        instructions.add(new Instruction("Thread pool idle loop", OpCode.READ_INSTR, new IndAddr("regA")));
        instructions.add(new Instruction(OpCode.RECEIVE, new RegAddr("regB")));
        // Check if this location contains 0 and if so, branch back to the first instruction of this loop
        instructions.add(new Instruction(OpCode.COMPUTE, new Operator("Equal"), new RegAddr("regB"), new RegAddr("reg0"), new RegAddr("regC")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regC"), new Rel(-3)));
        // If the location did not contain 0, then branch to the memory address that is given
        instructions.add(new Instruction(OpCode.JUMP, new Ind("regB")));

        return instructions;
    }

    /**
     * Generate a list of SPRIL instructions that can be run to check for a thrown error.
     * The error ID thrown is stored in regF, and based on the value of the register we print the corresponding
     * error message and terminate the program
     * @return spril instruction list
     */
    private List<Instruction> generateErrorHandling() {
        List<Instruction> instructions = new ArrayList<>();

        for (Error err: Error.values()) {
            List<Instruction> errInstructions = err.toSpril();

            // Check if this error was thrown
            instructions.add(new Instruction(OpCode.LOAD, new ImmValue(err.getId()), new RegAddr("regA")));
            instructions.add(new Instruction(OpCode.COMPUTE, new Operator("NEq"), new RegAddr("regF"), new RegAddr("regA"), new RegAddr("regB")));
            instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regB"), new Rel(errInstructions.size()+1)));

            instructions.addAll(errInstructions);
        }

        instructions.add(new Instruction(OpCode.ENDPROG));

        return instructions;
    }

    /**
     * Generate a list of spril instructions that can be run to execute the program
     * These spril instructions will be of the intermediate representation, meaning that any jump or branch instructions
     * are pointers to other instructions instead of numeric values, and it may contain ForkPlaceholderInstructions
     * @return spril instruction list
     * @requires the statements and threads variables to be set up
     */
    public List<Instruction> toSpril() {
        // Program structure
        //-(Branch to thread idle loop if not main thread)
        //-Code for main thread
        //-(Jump to endProg)
        //-(Code for thread 2)
        //-(Set thread_done flag)
        //-(Jump to endProg)
        //-(Code for thread 3)
        //-....
        //-(Thread Idle loop)
        //-EndProg
        List<Instruction> instructions = new ArrayList<>();

        // Create instructions for the loop and for EndProg, but don't add them to the code yet
        // We use them as branch Targets
        List<Instruction> threadIdleLoop = generateThreadIdleLoop();
        Instruction endProgInstr = new Instruction(OpCode.ENDPROG);

        // If there's multiple threads, we add an instruction at the start that lets all threads with id non-zero branch to the idle loop
        if (!threads.isEmpty()) {
            instructions.add(new Instruction("Make all threads other than main jump to the idle loop",
                    OpCode.BRANCH, new RegAddr("regSprID"), new TargetPtr(threadIdleLoop.get(0))));
        }

        // Add all instructions for the main thread
        for (Statement stmnt : statements) {
            instructions.addAll(stmnt.toSpril());
        }
        // let the main thread jump to endProg if there are multiple threads
        if (!threads.isEmpty()) instructions.add(new Instruction(OpCode.JUMP, new TargetPtr(endProgInstr)));

        // Add instructions for the other threads
        Map<String, Instruction> firstInstructionOfThread = new HashMap<>();
        for (Map.Entry<ForkVar, List<Statement>> thread : threads.entrySet()) {
            ForkVar threadVar = thread.getKey();
            List<Statement> threadStatements = thread.getValue();

            // Each thread starts with a NOP instruction that it'll branch to when fork is called
            Instruction firstInstr = new Instruction(OpCode.NOP);
            firstInstructionOfThread.put(threadVar.getId(), firstInstr);
            instructions.add(firstInstr);

            // Add instructions for thread
            List<Instruction> threadInstructions = new ArrayList<>();
            for (Statement stmnt : threadStatements) {
                threadInstructions.addAll(stmnt.toSpril());
            }
            if (threadInstructions.size() > 0) threadInstructions.get(0).addComment("Start of thread " + threadVar.getId());
            instructions.addAll(threadInstructions);

            // Set a flag that the thread is done, in case anyone is waiting on join(thread)
            instructions.add(new Instruction("Set thread_done flag for join("+threadVar.getId()+")", OpCode.LOAD, new ImmValue(-1), new RegAddr("regA")));
            instructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("regA"), new DirAddr(threadVar.getAddr())));

            // Jump to endProg afterwards
            instructions.add(new Instruction(OpCode.JUMP, new TargetPtr(endProgInstr)));
        }

        // Now add the idle loop and EndProg instructions
        if (!threads.isEmpty()) instructions.addAll(threadIdleLoop);
        instructions.add(endProgInstr);

        // Each fork statement has to set the value that the corresponding thread should jump to
        // These target instruction addresses will now be filled in
        for (Instruction instr: instructions) {
            if (instr instanceof ForkPlaceholderInstruction) {
                ForkPlaceholderInstruction forkInstr = (ForkPlaceholderInstruction) instr;
                String threadName = forkInstr.getThreadName();
                Instruction firstThreadInstr = firstInstructionOfThread.get(threadName);
                forkInstr.setJumpTargetPtr(firstThreadInstr);
            }
        }

        // Add code to handle thrown errors
        List<Instruction> errorHandling;
        if (ERROR_HANDLING) {
            errorHandling = generateErrorHandling();
            errorHandling.get(0).addComment("Error Handling");
        }
        else {
            errorHandling = List.of(new Instruction(OpCode.ENDPROG));
        }
        instructions.addAll(errorHandling);
        // Add the first instruction in errorHandling to BranchToErrorPlaceholders
        for (Instruction instr: instructions) {
            if (instr instanceof BranchToErrorPlaceholderInstruction) {
                ((BranchToErrorPlaceholderInstruction) instr).setJumpTargetPtr(errorHandling.get(0));
            }
        }

        return instructions;
    }
}
