package core;

public class AddItemCommand implements Command {
    private BillDirector billDirector;
    private Item item;
    private int quantity;

    public AddItemCommand(BillDirector billDirector, Item item, int quantity) {
        this.billDirector = billDirector;
        this.item = item;
        this.quantity = quantity;
    }

    @Override
    public void execute() {
        billDirector.addItemToBill(item, quantity);
    }


    @Override
    public void undo() {
        billDirector.removeItemFromBill(String.valueOf(item.getItemCode()), quantity);
    }

}