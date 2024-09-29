package org.example;

public class CheckStateService {

    private StateContext stateContext;

    public CheckStateService(StateContext stateContext) {
        this.stateContext = stateContext;
    }

    public void checkState() {
        stateContext.checkState();
    }

}
