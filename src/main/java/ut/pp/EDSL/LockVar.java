package ut.pp.EDSL;

public class LockVar extends Variable{
    private boolean isLocked;
    public LockVar(String id, Types type, boolean hasValue, int addr) {
        super(id, type, hasValue, addr,true);
        isLocked = false;
    }

    public boolean isLocked() {
        return isLocked;
    }

    public void setJoined(boolean isLocked) {
        this.isLocked = isLocked;
    }
}
