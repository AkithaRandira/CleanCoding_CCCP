package org.example;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public interface BillGraphicalUnitInterface {
    JComboBox<String> getPaymentStrategyComboBox();

    JFrame getFrame();

    JTextField getTfBillSerialNumber();

    JTextField getTfItemCode();

    JTextField getTfQuantity();

    JTextField getTfDiscount();

    JTextField getTfCashTendered();

    JPanel getItemsPanel();

    JScrollPane getScrollPane();

    JTextField getTfSubtotal();

    Map<String, PaymentStrategy> getStrategyMap();

    void addItemListener(ActionListener listener);

    void finalizeBillListener(ActionListener listener);

    JTextField getCustomerName();

    void setCustomerName(JTextField customerName);

    void createGUI(Map<String, PaymentStrategy> strategyMap);
}
