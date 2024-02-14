package ut.pp.EDSL;

import ut.pp.SPRIL.*;

import java.util.ArrayList;
import java.util.List;

/**
 * Class LoadVar holds the necessary information to generate spril code that can load a variable from memory
 * into a register
 */
public class LoadVar extends Expression {
    private SingleVar var;

    /**
     * LoadVar constructor
     * @param var   the variable
     */
    public LoadVar(SingleVar var) {
        this.var = var;
    }

    /**
     * Is the variable an index of an array?
     * @return boolean isArrayVar
     */
    public boolean isArrayVar() {
        return var.getAddressOffset() != null;
    }

    /**
     * Generate spril code that can be run to load the value of the variable from memory into the targetReg
     * given by the superclass Expression
     * @return Spril instruction list
     */
    @Override
    public List<Instruction> toSpril() {
        List<Instruction> instructions = new ArrayList<>();

        // Load value from memory and store in targetReg
        if (var.getAddressOffset() == null) {
            if (var.isShared()) {
                instructions.add(new Instruction("(Loading shared var " + var.getId() + ")",
                        OpCode.READ_INSTR, new DirAddr(var.getAddr())));
                instructions.add(new Instruction(OpCode.RECEIVE, targetReg));
            }
            else {
                instructions.add(new Instruction("(Loading var " + var.getId() + ")",
                        OpCode.LOAD, new DirAddr(var.getAddr()), targetReg));
            }
        }
        // If we're accessing a value from an array index, we need to calculate the result of the expression and
        // use the result as an offset for the memory address
        else {
            Expression addressOffset = var.getAddressOffset();
            if (addressOffset instanceof LoadConst) {
                int offset = ((LoadConst)addressOffset).getValue();
                int address = var.getAddr() + offset;
                if (var.isShared()) {
                    instructions.add(new Instruction("(Loading array index "+var.getId()+" at offset "+offset+")",
                            OpCode.READ_INSTR, new DirAddr(address)));
                    instructions.add(new Instruction(OpCode.RECEIVE, targetReg));
                }
                else {
                    instructions.add(new Instruction("(Loading array index "+var.getId()+" at offset "+offset+")",
                            OpCode.LOAD, new DirAddr(address), targetReg));
                }
            }
            else {
                // Evaluate expression
                addressOffset.setTargetReg("regA");
                instructions.addAll(addressOffset.toSpril());
                instructions.get(0).addComment("Calculating offset for reading from variable " + var.getId());

                //Check if the index is smaller than 0
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Lt"),
                        new RegAddr("regA"), new RegAddr("reg0"), new RegAddr("regB")));
                //Check if the index is bigger or equal to the length of the array
                instructions.add(new Instruction(OpCode.LOAD, new ImmValue(var.getMaxArrayIndex()), new RegAddr("regC")));
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Gt"),
                        new RegAddr("regA"), new RegAddr("regC"), new RegAddr("regC")));
                //If either condition was true, branch to error
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Or"),
                        new RegAddr("regB"), new RegAddr("regC"), new RegAddr("regB")));
                instructions.add(new BranchToErrorPlaceholderInstruction("regB", Error.ArrayIndexOutOfBounds));

                // Add instructions to load from array variable
                instructions.add(new Instruction(OpCode.LOAD, new ImmValue(var.getAddr()), new RegAddr("regB")));
                instructions.add(new Instruction(OpCode.COMPUTE, new ut.pp.SPRIL.Operator("Add"), new RegAddr("regA"), new RegAddr("regB"), new RegAddr("regA")));
                if (var.isShared()) {
                    instructions.add(new Instruction("(Loading array index "+var.getId()+" at calculated offset)",
                            OpCode.READ_INSTR, new IndAddr("regA")));
                    instructions.add(new Instruction(OpCode.RECEIVE, targetReg));
                }
                else {
                    instructions.add(new Instruction("(Loading array index " + var.getId() + " at calculated offset)",
                            OpCode.LOAD, new IndAddr("regA"), targetReg));
                }

            }
        }

        // push result to stack
        if (pushResultToStack) {
            instructions.add(new Instruction(OpCode.PUSH, targetReg));
        }

        return instructions;
    }
}
