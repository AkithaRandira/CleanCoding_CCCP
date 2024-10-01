package View;

import core.Customer;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.Map;

public class CustomerView extends JFrame {

    private JTextField customerFirstName = new JTextField(20);
    private JTextField customerLastName = new JTextField(20);
    private JButton addCustomer = new JButton("Add");
    private JTextField customerName = new JTextField(50);

    private JComboBox<String> customerTypes;
    private Map<String, Customer> customerMap;

    public CustomerView() {
        JPanel customerPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        customerPanel.add(new JLabel("First Name:"));
        customerPanel.add(customerFirstName);
        customerPanel.add(new JLabel("Last Name:"));
        customerPanel.add(customerLastName);
        customerPanel.add(addCustomer);
        customerPanel.add(new JLabel("Customer Name:"));
        customerPanel.add(customerName);

        customerPanel.add(new JLabel("Select Customer Type:"));
        String[] types = {"Regular", "Loyalty"};
        customerTypes = new JComboBox<>(types);
        customerPanel.add(customerTypes);

        this.add(customerPanel);
    }

    public String getCustomerFirstName() {
        return customerFirstName.getText();
    }

    public String getCustomerLastName() {
        return customerLastName.getText();
    }

    public String getCustomerType() {
        return customerTypes.getSelectedItem().toString();
    }

    public void setCustomerType(String customerType) {
        customerTypes.setSelectedItem(customerType);
    }
    public void setCustomerName(String name) {
        customerName.setText(name);
    }

    public void addCustomerListener(ActionListener listener) {
        addCustomer.addActionListener(listener);
    }

    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}
