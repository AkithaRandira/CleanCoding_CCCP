package org.example;

public class RemoveItemCommand implements Command{


    private BillDirector billDirector;
    private Item item;
    private int quantity;

    public RemoveItemCommand(BillDirector billDirector, Item item, int quantity) {
        this.billDirector = billDirector;
        this.item = item;
        this.quantity=quantity;
    }

    @Override
    public void execute() {
        billDirector.removeItemFromBill(String.valueOf(item.getItemCode()), quantity);

    }

    @Override
    public void undo() {
        billDirector.addItemToBill(item, quantity);

    }
}
