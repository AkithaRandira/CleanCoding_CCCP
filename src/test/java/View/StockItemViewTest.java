package View;

import core.Product;
import junit.framework.TestCase;

import javax.swing.*;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StockItemViewTest extends TestCase {

    private StockItemView stockItemView;

    protected void setUp() {
        stockItemView = new StockItemView();
    }

    public void testSetProductDropdown() {
        List<Product> mockProductList = new ArrayList<>();
        Product product1 = new Product(1, "Product 1");
        Product product2 = new Product(2, "Product 2");
        mockProductList.add(product1);
        mockProductList.add(product2);

        JComboBox<Product> mockComboBox = mock(JComboBox.class);


        stockItemView.setProductDropdown(mockProductList);
        verify(mockComboBox, times(2)).addItem(any(Product.class));
    }

    public void testGetItemCode() {
        JTextField mockItemCodeField = mock(JTextField.class);
        when(mockItemCodeField.getText()).thenReturn("ABC123");


        String itemCode = stockItemView.getItemCode();
        assertEquals("Item code should be 'ABC123'", "ABC123", itemCode);
    }

    public void testGetBatchCode() {
        JTextField mockBatchCodeField = mock(JTextField.class);
        when(mockBatchCodeField.getText()).thenReturn("1001");


        int batchCode = stockItemView.getBatchCode();
        assertEquals("Batch code should be 1001", 1001, batchCode);
    }

    public void testGetSelectedProduct() {
        Product mockProduct = new Product(1, "Product A");
        JComboBox<Product> mockComboBox = mock(JComboBox.class);
        when(mockComboBox.getSelectedItem()).thenReturn(mockProduct);


        Product selectedProduct = stockItemView.getSelectedProduct();
        assertEquals("Selected product should be 'Product A'", mockProduct, selectedProduct);
    }

    public void testGetQuantityInStock() {
        JTextField mockQuantityField = mock(JTextField.class);
        when(mockQuantityField.getText()).thenReturn("10");


        JTextField quantityInStock = stockItemView.getQuantityInStock();
        assertEquals("Quantity in stock should be '10'", mockQuantityField, quantityInStock);
    }

    public void testGetExpiryDate() {
        JTextField mockExpiryDateField = mock(JTextField.class);
        when(mockExpiryDateField.getText()).thenReturn("2024-12-31");


        String expiryDate = stockItemView.getExpiryDate();
        assertEquals("Expiry date should be '2024-12-31'", "2024-12-31", expiryDate);
    }

    public void testGetManufactureDate() {
        JTextField mockManufactureDateField = mock(JTextField.class);
        when(mockManufactureDateField.getText()).thenReturn("2024-01-01");


        String manufactureDate = stockItemView.getManufactureDate();
        assertEquals("Manufacture date should be '2024-01-01'", "2024-01-01", manufactureDate);
    }

    public void testGetBatchDate() {
        JTextField mockBatchDateField = mock(JTextField.class);
        when(mockBatchDateField.getText()).thenReturn("2024-01-15");


        String batchDate = stockItemView.getBatchDate();
        assertEquals("Batch date should be '2024-01-15'", "2024-01-15", batchDate);
    }

    public void testAddBatchItemListener() {
        ActionListener mockListener = mock(ActionListener.class);
        JButton mockAddButton = mock(JButton.class);


        stockItemView.addBatchItemListener(mockListener);
        verify(mockAddButton).addActionListener(mockListener);
    }

    public void testDisplaySuccessMessage() {
        JOptionPane mockPane = mock(JOptionPane.class);
        stockItemView.displaySuccessMessage("Batch item added successfully");
        mockPane.showMessageDialog(stockItemView, "Batch item added successfully");
    }

    public void testDisplayError() {
        JOptionPane mockPane = mock(JOptionPane.class);
        stockItemView.displayError("Error adding batch item");
        mockPane.showMessageDialog(stockItemView, "Error adding batch item");
    }
}
