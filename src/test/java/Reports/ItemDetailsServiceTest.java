package Reports;



import core.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ItemDetailsServiceTest {

    private ItemDetailsService itemDetailsService;

    @Before
    public void setUp() throws Exception {
        // Initialize any dependencies or resources needed for testing
        ProductDetailsService productDetailsService = new ProductDetailsService();
        itemDetailsService = new ItemDetailsService(productDetailsService);
    }

    @After
    public void tearDown() throws Exception {
        // Clean up any resources used for testing
    }

    @Test
    public void fetchItemDetails() {
        // Assuming item with itemCode 1 exists in the database
        int itemCode = 1;

        // Call the fetchItemDetails method
        Item item = itemDetailsService.fetchItemDetails(String.valueOf(itemCode));

        // Check if item is not null
        assertNotNull(item);

//        // Check if the itemCode matches the expected value
//        assertEquals(itemCode, (int) item.getItemCode());

        // Add more assertions as needed to validate the retrieved item details
        // For example, you can check if itemDescription matches the expected value
        assertEquals("new addition", item.getItemDescription());

    }

    @Test
    public void testFetchNonExistentItemDetails() {
        // Assuming item with itemCode -1 does not exist in the database
        int nonExistentItemCode = -1;

        // Call the fetchItemDetails method for a non-existent item
        Item nonExistentItem = itemDetailsService.fetchItemDetails(String.valueOf(nonExistentItemCode));

        // Assert that the returned item is null
        assertNull(nonExistentItem);
    }

}

