package View;

import core.Product;
import junit.framework.TestCase;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ItemsOnShelfViewTest extends TestCase {

    private ItemsOnShelfView itemsOnShelfView;

    protected void setUp() {
        itemsOnShelfView = new ItemsOnShelfView();
    }

    public void testGetItemCode() {
        JTextField mockItemCodeField = mock(JTextField.class);
        when(mockItemCodeField.getText()).thenReturn("12345");


        String itemCode = itemsOnShelfView.getItemCode();
        assertEquals("Item code should be '12345'", "12345", itemCode);
    }

    public void testGetItemDescription() {
        JTextField mockItemDescriptionField = mock(JTextField.class);
        when(mockItemDescriptionField.getText()).thenReturn("Sample Item");


        String itemDescription = itemsOnShelfView.getItemDescription();
        assertEquals("Item description should be 'Sample Item'", "Sample Item", itemDescription);
    }

    public void testGetUnitPrice() {
        JTextField mockUnitPriceField = mock(JTextField.class);
        when(mockUnitPriceField.getText()).thenReturn("50.0");

        String unitPrice = itemsOnShelfView.getUnitPrice();
        assertEquals("Unit price should be '50.0'", "50.0", unitPrice);
    }

    public void testGetQuantityOnShelf() {
        JTextField mockQtyOnShelfField = mock(JTextField.class);
        when(mockQtyOnShelfField.getText()).thenReturn("10");


        String quantityOnShelf = itemsOnShelfView.getQuantityOnShelf();
        assertEquals("Quantity on shelf should be '10'", "10", quantityOnShelf);
    }

    public void testGetProductID() {
        JComboBox<String> mockComboBox = mock(JComboBox.class);
        when(mockComboBox.getSelectedItem()).thenReturn("1 - Product A");


        String productID = itemsOnShelfView.getProductID();
        assertEquals("Product ID should be '1'", "1", productID);
    }

    public void testSetProducts() {
        List<Product> mockProductList = new ArrayList<>();
        Product product1 = new Product(1, "Product A");
        Product product2 = new Product(2, "Product B");
        mockProductList.add(product1);
        mockProductList.add(product2);

        JComboBox<String> mockComboBox = mock(JComboBox.class);


        itemsOnShelfView.setProducts(mockProductList);

        verify(mockComboBox).addItem("1 - Product A");
        verify(mockComboBox).addItem("2 - Product B");
    }

    public void testAddItemsOnShelfListener() {
        ActionListener mockListener = mock(ActionListener.class);
        JButton mockAddButton = mock(JButton.class);

        itemsOnShelfView.addItemsOnShelfListener(mockListener);
        verify(mockAddButton).addActionListener(mockListener);
    }

    public void testAddRestockShelfListener() {
        ActionListener mockListener = mock(ActionListener.class);
        JButton mockRestockButton = mock(JButton.class);


        itemsOnShelfView.addRestockShelfListener(mockListener);
        verify(mockRestockButton).addActionListener(mockListener);
    }

    public void testDisplayErrorMessage() {
        JOptionPane mockPane = mock(JOptionPane.class);
        itemsOnShelfView.displayErrorMessage("Error occurred");
        mockPane.showMessageDialog(itemsOnShelfView, "Error occurred");
    }

    public void testDisplaySuccessMessage() {
        JOptionPane mockPane = mock(JOptionPane.class);
        itemsOnShelfView.displaySuccessMessage("Item added successfully");
        mockPane.showMessageDialog(itemsOnShelfView, "Item added successfully");
    }
}
