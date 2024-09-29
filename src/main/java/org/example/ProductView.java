package org.example;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.List;

public class ProductView extends JFrame {
    private JTextField productName = new JTextField(20);
    private JComboBox<ProductCategory> productCategory = new JComboBox<>();
    private JButton addProduct = new JButton("Add");
    private JTextField newProduct = new JTextField(50);

    public ProductView() {
        JPanel productPanel = new JPanel();
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setSize(600, 200);
        productPanel.add(new JLabel("Product Name:"));
        productPanel.add(productName);
        productPanel.add(new JLabel("Category:"));
        productPanel.add(productCategory);
        productPanel.add(addProduct);
        productPanel.add(new JLabel("Product:"));
        productPanel.add(newProduct);

        this.add(productPanel);
    }

    public String getProductName() {
        return productName.getText();
    }

    public  Integer getProductCategoryID(){
        return  getProductCategoryID();
    }

    public ProductCategory getSelectedProductCategory() {
        return (ProductCategory) productCategory.getSelectedItem();
    }

    public void setProduct(String name, String productCategory) {

        newProduct.setText(name);
    }

    public void setProductCategories(List<ProductCategory> categories) {
        for (ProductCategory category : categories) {
            productCategory.addItem(category);
        }
    }

    public void addProductListener(ActionListener listener) {
        addProduct.addActionListener(listener);
    }
    public void displaySuccessMessage(String message) {
        JOptionPane.showMessageDialog(this, message);
    }

    public void displayError(String errorMessage) {
        JOptionPane.showMessageDialog(this, errorMessage);
    }
}

