package ut.pp.EDSL;

import ut.pp.SPRIL.DirAddr;
import ut.pp.SPRIL.Instruction;
import ut.pp.SPRIL.OpCode;
import ut.pp.SPRIL.RegAddr;

import java.util.ArrayList;
import java.util.List;

/**
 * Class Declaration has all information to compiling declaration statements
 * Declarations have the form (Shared)? [type] [varName] (, [varName])*
 * When declaring a variable, we assign a default value
 */
public class Declaration implements Statement {
    private List<Variable> vars;

    /**
     * Constructor
     * @param vars  List of variables that are declared
     */
    public Declaration(List<Variable> vars) {
        this.vars = vars;
    }

    /**
     * Generate spril code that assigns default values to the variables
     * @return list of SPRIL instructions
     */
    @Override
    public List<Instruction> toSpril() {
        List<Instruction> instructions = new ArrayList<>();
        for (Variable var: vars) {
            List<Instruction> declInstructions = new ArrayList<>();
            if (var instanceof SingleVar) {
                if (var.isShared()) {
                    declInstructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("reg0"), new DirAddr(var.getAddr())));
                }
                else {
                    declInstructions.add(new Instruction(OpCode.STORE, new RegAddr("reg0"), new DirAddr(var.getAddr())));
                }
            }
            else { // var instanceof ArrayVar
                ArrayVar arrayVar = (ArrayVar) var;
                for (int offset = 0; offset < arrayVar.getLength(); offset++) {
                    int memAddress = arrayVar.getAddr() + offset;
                    if (var.isShared()) {
                        declInstructions.add(new Instruction(OpCode.WRITE_INSTR, new RegAddr("reg0"), new DirAddr(memAddress)));
                    }
                    else {
                        declInstructions.add(new Instruction(OpCode.STORE, new RegAddr("reg0"), new DirAddr(memAddress)));
                    }
                }
            }
            declInstructions.get(0).addComment("Declaration of " + var.getId());
            instructions.addAll(declInstructions);
        }
        return instructions;
    }
}
