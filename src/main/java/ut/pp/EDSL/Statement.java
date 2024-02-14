package ut.pp.EDSL;

import ut.pp.SPRIL.Instruction;

import java.util.List;

/**
 * Interface for all objects representing Statements
 */
public interface Statement {
    public List<Instruction> toSpril();
}
