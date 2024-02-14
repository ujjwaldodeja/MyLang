package ut.pp.EDSL;

public class ForkVar extends Variable{
    private boolean isJoined;
    public ForkVar(String id, Types type, boolean hasValue, int addr) {
        super(id, type, hasValue, addr,true);
        isJoined = false;
    }

    public boolean isJoined() {
        return isJoined;
    }

    public void setJoined(boolean joined) {
        isJoined = joined;
    }
}
