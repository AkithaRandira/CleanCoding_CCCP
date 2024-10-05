package Controller;

import core.Item;
import core.ItemRepository;
import core.Product;
import core.ProductIterator;
import View.ItemsOnShelfView;
import junit.framework.TestCase;

import java.awt.event.ActionEvent;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class ItemsOnShelfControllerTest extends TestCase {

    private ItemsOnShelfController controller;
    private ItemsOnShelfView mockView;
    private Item mockModel;
    private ItemRepository mockItemRepository;
    private ProductIterator mockProductIterator;

    protected void setUp() {
        mockView = mock(ItemsOnShelfView.class);
        mockModel = mock(Item.class);
        mockItemRepository = mock(ItemRepository.class);
        mockProductIterator = mock(ProductIterator.class);
        controller = new ItemsOnShelfController(mockView, mockModel, mockItemRepository);
    }

    public void testInitializeProducts() {
        List<Product> mockProductList = new ArrayList<>();
        Product product1 = new Product(1, "Product A");
        Product product2 = new Product(2, "Product B");
        mockProductList.add(product1);
        mockProductList.add(product2);

        when(mockProductIterator.hasNext()).thenReturn(true, true, false);
        when(mockProductIterator.next()).thenReturn(product1, product2);

        controller = new ItemsOnShelfController(mockView, mockModel, mockItemRepository);
        verify(mockView).setProducts(mockProductList);
    }

    public void testRestockShelfListener() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        ItemsOnShelfController.RestockShelfListener listener = controller.new RestockShelfListener();

        doNothing().when(mockItemRepository).reStockShelf();
        listener.actionPerformed(mockEvent);

        verify(mockItemRepository).reStockShelf();
        verify(mockView).displaySuccessMessage("Shelf restocked successfully!");
    }

    public void testRestockShelfListenerWithError() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        ItemsOnShelfController.RestockShelfListener listener = controller.new RestockShelfListener();

        doThrow(new RuntimeException("Restock failed")).when(mockItemRepository).reStockShelf();
        listener.actionPerformed(mockEvent);

        verify(mockItemRepository).reStockShelf();
        verify(mockView).displayErrorMessage("Error restocking shelf: Restock failed");
    }

    public void testAddItemListenerSuccess() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        ItemsOnShelfController.AddItemListener listener = controller.new AddItemListener();

        when(mockView.getItemCode()).thenReturn("1001");
        when(mockView.getItemDescription()).thenReturn("Sample Item");
        when(mockView.getUnitPrice()).thenReturn("50.0");
        when(mockView.getQuantityOnShelf()).thenReturn("10");
        when(mockView.getProductID()).thenReturn("1");

        listener.actionPerformed(mockEvent);

        verify(mockItemRepository).addItemsOnShelf(
                eq(1001),
                eq("Sample Item"),
                eq(50.0),
                eq(10),
                any(Product.class)
        );
        verify(mockView).displaySuccessMessage("Item added successfully!");
    }

    public void testAddItemListenerWithError() {
        ActionEvent mockEvent = mock(ActionEvent.class);
        ItemsOnShelfController.AddItemListener listener = controller.new AddItemListener();

        when(mockView.getItemCode()).thenReturn("invalid");
        listener.actionPerformed(mockEvent);

        verify(mockView).displayErrorMessage(anyString());
    }
}
