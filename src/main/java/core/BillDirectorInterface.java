package core;

import javax.swing.*;

public interface BillDirectorInterface {
    void addItem();

    void addItemToBill(Item item, int quantity);

    void addItemToPanel(BillItem billItem);

    void removeItemFromBill(String itemCode, JPanel itemPanel);

    void removeItemFromBill(int itemCode, JPanel itemPanel);

    void removeItemFromBill(String itemCode, int quantity);

    void finalizeBill();
}
