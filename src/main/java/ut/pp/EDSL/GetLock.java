package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Class GetLock holds all necessary information to compile a get_lock call of the form get_lock([lockname]);
 */
public class GetLock implements Statement {
    private LockVar var;

    /**
     * GetLock constructor
     * @param var  the identifier of the lock
     */
    public GetLock(LockVar var) {
        this.var = var;
    }

    /**
     * Generate the spril code that can be run in order to wait for a lock and get exclusive access to it when it's available
     * @return List of spril instructions
     */
    @Override
    public List<Instruction> toSpril() {
        //TestAndSet (DirAddr 6)
        //Receive (RegAddr regA)
        //Compute Equal regA reg0 regA
        //Branch regA #testAndSet
        List<Instruction> instructions = new ArrayList<>();
        instructions.add(new Instruction("Try to get lock("+var.getId()+")", OpCode.TEST_AND_SET, new DirAddr(var.getAddr())));
        instructions.add(new Instruction(OpCode.RECEIVE, new RegAddr("regA")));
        instructions.add(new Instruction(OpCode.COMPUTE, new Operator("Equal"), new RegAddr("regA"), new RegAddr("reg0"), new RegAddr("regA")));
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regA"), new TargetPtr(instructions.get(0))));

        return instructions;
    }
}
