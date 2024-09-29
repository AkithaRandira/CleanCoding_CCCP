package org.example;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.HashMap;

public class BillDirector implements BillDirectorInterface {
    private StateContext stateContext;
    private BillBuilder builder;
    private Map<String, PaymentStrategy> strategyMap;
    private int totalQuantitiesSold;
    private List<BillItem> billItems = new ArrayList<>();
    private double subTotal = 0.0;
    private List<Command> commands = new ArrayList<>();
    private ItemDetailsService itemDetailsService;
    private BillSaveService billSaveService;

    private BillGraphicalUnitInterfaceService billGraphicalUnitInterfaceService;

    private CheckStateService checkStateService;

    public BillDirector(BillBuilder builder, ItemDetailsService itemDetailsService, BillSaveService billSaveService, BillGraphicalUnitInterfaceService billGraphicalUnitInterfaceService, CheckStateService checkStateService) {
        this.builder = builder;
        this.stateContext = new StateContext();
        this.itemDetailsService = itemDetailsService;
        this.billSaveService = billSaveService;
        this.billGraphicalUnitInterfaceService = billGraphicalUnitInterfaceService;
        this.checkStateService = checkStateService;
        this.strategyMap = new HashMap<>();
        strategyMap.put("Cash", new CashPayment());
        strategyMap.put("Credit Card", new CreditCardPayment());

        billGraphicalUnitInterfaceService.addItemListener(e -> addItem());
        billGraphicalUnitInterfaceService.finalizeBillListener(e -> finalizeBill());
    }

    public List<BillItem> getBillItems() {
        return billItems;
    }

    public double getSubTotal() {
        return subTotal;
    }

    @Override
    public void addItem() {
        try {
            String itemCode = billGraphicalUnitInterfaceService.getTfItemCode().getText(); // Changed to String
            int quantity = Integer.parseInt(billGraphicalUnitInterfaceService.getTfQuantity().getText());
            Item item = itemDetailsService.fetchItemDetails(Integer.parseInt(itemCode));

            if (item != null) {
                Command addItemCommand = new AddItemCommand(this, item, quantity);
                commands.add(addItemCommand);
                addItemCommand.execute();
            } else {
                JOptionPane.showMessageDialog(billGraphicalUnitInterfaceService.getFrame(), "Item with code " + itemCode + " not found.");
            }
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(billGraphicalUnitInterfaceService.getFrame(), "Invalid input. Please enter valid numbers for item code and quantity.");
        }
    }

    @Override
    public void addItemToBill(Item item, int quantity) {
        String itemCode = String.valueOf(item.getItemCode()); // Make sure this is a String
        String itemName = item.getProduct().getProductName();
        double totalPrice = item.getUnitPrice() * quantity;
        totalQuantitiesSold += quantity;
        subTotal += totalPrice;

        // Passing itemCode as a String
        BillItem billItem = new BillItem(itemCode, itemName, quantity, item.getUnitPrice(), totalPrice, item);
        billItems.add(billItem);
        addItemToPanel(billItem);

        billGraphicalUnitInterfaceService.getTfSubtotal().setText(String.format("%.2f", subTotal));
    }

    @Override
    public void addItemToPanel(BillItem billItem) {
        JPanel itemPanel = new JPanel();
        itemPanel.setLayout(new BoxLayout(itemPanel, BoxLayout.X_AXIS));

        JLabel itemLabel = new JLabel(String.format("Item: %s, Quantity: %d, Unit Price: %.2f, Total Price: %.2f",
                billItem.getItemName(), billItem.getQuantity(), billItem.getUnitPrice(), billItem.getTotalPrice()));

        JButton removeButton = new JButton("Remove");
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeItemFromBill(billItem.getItemCode(), itemPanel);
            }
        });

        itemPanel.add(itemLabel);
        itemPanel.add(removeButton);

        billGraphicalUnitInterfaceService.getItemsPanel().add(itemPanel);
        billGraphicalUnitInterfaceService.getItemsPanel().revalidate();
        billGraphicalUnitInterfaceService.getItemsPanel().repaint();
    }

    @Override
    public void removeItemFromBill(String itemCode, JPanel itemPanel) {
        BillItem itemToRemove = null;
        for (BillItem item : billItems) {
            if (item.getItemCode().equals(itemCode)) { // Use .equals() for String comparison
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            Command removeItemCommand = new RemoveItemCommand(this, itemToRemove.getItem(), itemToRemove.getQuantity());
            removeItemCommand.execute();
            commands.add(removeItemCommand);
        } else {
            JOptionPane.showMessageDialog(billGraphicalUnitInterfaceService.getFrame(), "Item with code " + itemCode + " not found in the bill.");
        }
    }

    @Override
    public void removeItemFromBill(int itemCode, JPanel itemPanel) {
        removeItemFromBill(String.valueOf(itemCode), itemPanel);
    }


    @Override
    public void removeItemFromBill(String itemCode, int quantity) {
        BillItem itemToRemove = null;
        for (BillItem item : billItems) {
            if (item.getItemCode().equals(itemCode)) { // Use .equals() for String comparison
                itemToRemove = item;
                break;
            }
        }

        if (itemToRemove != null) {
            billItems.remove(itemToRemove);
            totalQuantitiesSold -= quantity;
            subTotal -= itemToRemove.getTotalPrice();

            billGraphicalUnitInterfaceService.getItemsPanel().removeAll();
            for (BillItem item : billItems) {
                addItemToPanel(item);
            }
            billGraphicalUnitInterfaceService.getItemsPanel().revalidate();
            billGraphicalUnitInterfaceService.getItemsPanel().repaint();

            billGraphicalUnitInterfaceService.getTfSubtotal().setText(String.format("%.2f", subTotal));
        } else {
            JOptionPane.showMessageDialog(billGraphicalUnitInterfaceService.getFrame(), "Item with code " + itemCode + " not found in the bill.");
        }
    }

    @Override
    public void finalizeBill() {
        stateContext.finalizeBill(this);
        checkStateService.checkState();
        try {
            int billSerialNumber = Integer.parseInt(billGraphicalUnitInterfaceService.getTfBillSerialNumber().getText());
            double discount = Double.parseDouble(billGraphicalUnitInterfaceService.getTfDiscount().getText());
            double cashTendered = Double.parseDouble(billGraphicalUnitInterfaceService.getTfCashTendered().getText());
            double netTotal = subTotal - discount;
            double changeAmount = cashTendered - netTotal;
            String selectedPaymentStrategy = (String) billGraphicalUnitInterfaceService.getPaymentStrategyComboBox().getSelectedItem();
            PaymentStrategy strategy = strategyMap.get(selectedPaymentStrategy);
            PaymentContext paymentContext = new PaymentContext(strategy);
            String customerName = billGraphicalUnitInterfaceService.getCustomerName().getText();

            Date dateOfBill = new Date();

            builder.addSerialNumber(billSerialNumber);
            builder.addBillItems(billItems);
            builder.addSubtotal(subTotal);
            builder.addDiscount(discount);
            builder.addNetTotal(netTotal);
            builder.addCashTendered(cashTendered);
            builder.addChangeAmount(changeAmount);
            builder.addDateOfBill(dateOfBill);
            builder.addTotalQuantitiesSold(totalQuantitiesSold);
            builder.addPaymentMethod(selectedPaymentStrategy);
            builder.addCustomerName(customerName);

            Bill bill = builder.getBill();
            paymentContext.executeStrategy(bill);
            billSaveService.saveBill(bill);

            billGraphicalUnitInterfaceService.getTfSubtotal().setText(String.format("Subtotal:", subTotal));
            JOptionPane.showMessageDialog(billGraphicalUnitInterfaceService.getFrame(), "Bill added successfully");
            stateContext.processBill(this);
        } catch (Exception e) {
            JOptionPane.showMessageDialog(billGraphicalUnitInterfaceService.getFrame(), "Error Adding bill.");
        }
    }
}
