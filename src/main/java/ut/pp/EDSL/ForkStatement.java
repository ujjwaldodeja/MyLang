package ut.pp.EDSL;

import ut.pp.SPRIL.ForkPlaceholderInstruction;
import ut.pp.SPRIL.Instruction;
import ut.pp.SPRIL.OpCode;

import java.util.List;

/**
 * Class ForkStatement holds all the necessary information to compile a fork statement
 * Fork statements are of the form "fork([threadName]) {[Statements]}
 */
public class ForkStatement implements Statement {
    private ForkVar var;
    private List<Statement> statements;

    /**
     * ForkStatement constructor
     * @param var           The identifier of the thread
     * @param statements    The list of statements that should be run
     */
    public ForkStatement(ForkVar var, List<Statement> statements) {
        this.var = var;
        this.statements = statements;
    }

    /**
     * Generate a placeholder instruction that can be replaced with actual Spril code in a later compilation step
     * The reason for this is that we don't know the address that new thread should jump to
     * @return Instruction List containing placeholder Instruction object
     */
    @Override
    public List<Instruction> toSpril() {
        // We put a placeholder that will be filled in by Program.toSpril
        return List.of(new ForkPlaceholderInstruction(var));
    }
}
