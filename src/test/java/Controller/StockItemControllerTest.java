package Controller;

import junit.framework.TestCase;



import core.Stock;
import core.Product;
import core.StockItemRepository;
import core.ProductRepository;
import View.StockItemView;
import junit.framework.TestCase;

import java.awt.event.ActionEvent;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StockItemControllerTest extends TestCase {

    private StockItemController stockItemController;
    private StockItemView mockView;
    private Stock mockModel;
    private StockItemRepository mockStockItemRepository;
    private ProductRepository mockProductRepository;
    private Product mockProduct;

    protected void setUp() {
        mockView = mock(StockItemView.class);
        mockModel = mock(Stock.class);
        mockStockItemRepository = mock(StockItemRepository.class);
        mockProductRepository = mock(ProductRepository.class);
        mockProduct = mock(Product.class);

        stockItemController = new StockItemController(mockView, mockModel, mockStockItemRepository, mockProductRepository);
    }

    public void testInitializeProductDropdown() {
        List<Product> products = new ArrayList<>();
        products.add(mockProduct);
        when(mockProductRepository.getAllProducts()).thenReturn(products);

        stockItemController.initializeProductDropdown();
        verify(mockView).setProductDropdown(products);
    }

    public void testAddBatchItemListenerSuccess() {
        when(mockView.getItemCode()).thenReturn("AB123");
        when(mockView.getBatchCode()).thenReturn(Integer.valueOf("B001"));
        when(mockView.getSelectedProduct()).thenReturn(mockProduct);
        when(mockProduct.getProductName()).thenReturn("Sample Product");
        when(mockView.getQuantityInStock().getText()).thenReturn("100");
        when(mockView.getExpiryDate()).thenReturn("2024-12-31");
        when(mockView.getManufactureDate()).thenReturn("2024-01-01");
        when(mockView.getBatchDate()).thenReturn("2024-01-10");

        ActionEvent mockEvent = mock(ActionEvent.class);
        StockItemController.AddBatchItemListener listener = stockItemController.new AddBatchItemListener();
        listener.actionPerformed(mockEvent);

        verify(mockStockItemRepository).addBatchItems(
                "B001", "AB123", "Sample Product", 100,
                Date.valueOf("2024-01-01"), Date.valueOf("2024-12-31"), Date.valueOf("2024-01-10")
        );
        verify(mockView).displaySuccessMessage("Batch item added successfully for product: Sample Product");
    }

    public void testAddBatchItemListenerInvalidItemCode() {
        when(mockView.getItemCode()).thenReturn("!nv@lid");

        ActionEvent mockEvent = mock(ActionEvent.class);
        StockItemController.AddBatchItemListener listener = stockItemController.new AddBatchItemListener();
        listener.actionPerformed(mockEvent);

        verify(mockView).displayError("Invalid input. Please enter a valid alphanumeric item code.");
    }

    public void testAddBatchItemListenerInvalidNumberFormat() {
        when(mockView.getItemCode()).thenReturn("AB123");
        when(mockView.getBatchCode()).thenReturn(Integer.valueOf("B001"));
        when(mockView.getSelectedProduct()).thenReturn(mockProduct);
        when(mockView.getQuantityInStock().getText()).thenReturn("invalid_number");

        ActionEvent mockEvent = mock(ActionEvent.class);
        StockItemController.AddBatchItemListener listener = stockItemController.new AddBatchItemListener();
        listener.actionPerformed(mockEvent);

        verify(mockView).displayError("Invalid input. Please enter valid numbers.");
    }

    public void testAddBatchItemListenerInvalidDateFormat() {
        when(mockView.getItemCode()).thenReturn("AB123");
        when(mockView.getBatchCode()).thenReturn(Integer.valueOf("B001"));
        when(mockView.getSelectedProduct()).thenReturn(mockProduct);
        when(mockView.getQuantityInStock().getText()).thenReturn("100");
        when(mockView.getExpiryDate()).thenReturn("31-12-2024"); // Invalid date format

        ActionEvent mockEvent = mock(ActionEvent.class);
        StockItemController.AddBatchItemListener listener = stockItemController.new AddBatchItemListener();

        try {
            listener.actionPerformed(mockEvent);
        } catch (IllegalArgumentException ex) {
            verify(mockView).displayError("Invalid date format. Please enter date in yyyy-MM-dd format.");
        }
    }

    public void testAddBatchItemListenerMissingProduct() {
        when(mockView.getItemCode()).thenReturn("AB123");
        when(mockView.getBatchCode()).thenReturn(Integer.valueOf("B001"));
        when(mockView.getSelectedProduct()).thenReturn(null); // No product selected

        ActionEvent mockEvent = mock(ActionEvent.class);
        StockItemController.AddBatchItemListener listener = stockItemController.new AddBatchItemListener();
        listener.actionPerformed(mockEvent);

        verify(mockView).displayError("Please select a product.");
    }
}
