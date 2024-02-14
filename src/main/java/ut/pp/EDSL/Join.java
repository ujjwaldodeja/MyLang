package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Join holds all necessary information for compiling a Join statement
 * Join is of the form join([threadname]);
 */
public class Join implements Statement {
    private ForkVar var;

    /**
     * Join constructor
     * @param var    identifier of the thread
     */
    public Join(ForkVar var) {
        this.var = var;
    }

    /**
     * Generate Spril code that can be run to wait for the specified thread to finish
     * @return List of spril instructions
     */
    @Override
    public List<Instruction> toSpril() {
        //ReadInstr (DirAddr [id])
        //Receive regA
        //Compute GtE regA reg0 regA
        //Branch regA (Abs [line0])
        List<Instruction> instructions = new ArrayList<>();
        // Read shared memory address with flag that will be set to -1 by the thread
        instructions.add(new Instruction("Wait on join("+var.getId()+")", OpCode.READ_INSTR, new DirAddr(var.getAddr())));
        instructions.add(new Instruction(OpCode.RECEIVE, new RegAddr("regA")));
        instructions.add(new Instruction(OpCode.COMPUTE, new Operator("GtE"), new RegAddr("regA"), new RegAddr("reg0"), new RegAddr("regA")));
        // If it's not -1, we repeat keep checking
        instructions.add(new Instruction(OpCode.BRANCH, new RegAddr("regA"), new TargetPtr(instructions.get(0))));

        return instructions;
    }
}
