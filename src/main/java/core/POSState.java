package core;

public interface POSState {
    void checkState(StateContext stateContext);

    default void finalizeBill(StateContext stateContext, BillDirector director) {
        throw new UnsupportedOperationException("FinalizeBill not supported in current state");
    }

    default void processBill(StateContext stateContext, BillDirector director) {
        throw new UnsupportedOperationException("ProcessBill not supported in current state");
    }

    default void idleBill(StateContext stateContext, BillDirector director) {
        throw new UnsupportedOperationException("IdleBill not supported in current state");
    }
}
