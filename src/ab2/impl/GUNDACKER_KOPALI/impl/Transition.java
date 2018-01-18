package ab2.impl.GUNDACKER_KOPALI.impl;

public class Transition {
    private int fromState;
    private String s;
    private int toState;

    public Transition(int fromState, String s, int toState){
        this.fromState = fromState;
        this.s = s;
        this.toState = toState;
    }

    public int getFromState(){
        return fromState;
    }
    public String getS(){
        return s;
    }
    public int getToState(){
        return toState;
    }
    public void setFromState(int state){
        this.fromState = state;
    }
    public void setToState(int state){
        this.toState = state;
    }
}
