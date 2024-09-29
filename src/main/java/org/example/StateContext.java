package org.example;

public class StateContext {
    private POSState state;

    public StateContext(){
        state=new IdleState();
    }

    public POSState getState() {
        return state;
    }

    public void setState(POSState state) {
        this.state = state;
    }

    public void checkState(){
        state.checkState(this);
    }

    public void finalizeBill(BillDirector director) {
        state.finalizeBill(this, director);
    }

    public void processBill(BillDirector director) {
        state.processBill(this, director);
    }

    public void idleBill(BillDirector director) {
        state.idleBill(this, director);
    }
}
