package org.example;

public class ProcessState implements POSState{

    @Override
    public void checkState(StateContext stateContext) {
        System.out.println("Initiating Billing process...");
    }

    @Override
    public void finalizeBill(StateContext stateContext, BillDirector director) {
        System.out.println("Complete Finalization");
        stateContext.setState(new FinalizedState());
    }

    @Override
    public void processBill(StateContext stateContext, BillDirector director) {
        System.out.println("Bill processed");
        stateContext.setState(new ProcessState());
    }

    @Override
    public void idleBill(StateContext stateContext, BillDirector director) {
        System.out.println("Moving to Idle State");
        stateContext.setState(new IdleState());
    }
}
