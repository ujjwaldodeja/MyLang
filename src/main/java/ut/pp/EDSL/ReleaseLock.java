package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class ReleaseLock holds all necessary information to compile a release_lock statement
 * of the form release_lock([lockName])
 */
public class ReleaseLock implements Statement {
    private LockVar var;

    /**
     * ReleaseLock constructor
     * @param var identifier of the lock
     */
    public ReleaseLock(LockVar var) {
        this.var = var;
    }

    /**
     * Generate spril code that can be run to give up your exclusive access to the lock
     * @return spril instruction list
     */
    @Override
    public List<Instruction> toSpril() {
        //Load (ImmValue 0) (RegAddr regA)
        //WriteInstr (RegAddr regA) (DirAddr 6)
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new Instruction("Release lock("+var.getId()+")", OpCode.LOAD, new ImmValue(0), new RegAddr("regA")));
        instructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("regA"), new DirAddr(var.getAddr())));

        return instructions;
    }
}
