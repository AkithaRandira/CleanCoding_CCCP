package Reports;



import core.*;
import junit.framework.TestCase;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ShelfHandlerTest extends TestCase {

    public void testHandleMovingItemsToTheShelf() {
        // Create a list of Stock items
        List<Stock> stockItems = new ArrayList<>();
        // Add some sample Stock items
//        Stock stockItem1 = new Stock(1, 123, "Item A", 1001, 10, Date.valueOf("2024-06-10"), Date.valueOf("2024-06-01"), Date.valueOf("2024-05-30"));
//        Stock stockItem2 = new Stock(2, 124, "Item B", 1002, 15, Date.valueOf("2024-06-15"), Date.valueOf("2024-06-01"), Date.valueOf("2024-05-30"));
//        stockItems.add(stockItem1);
//        stockItems.add(stockItem2);


        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outputStream));


        ExpiryDateHandler expiryDateHandler = new ExpiryDateHandler();
        ShelfHandler shelfHandler = new ShelfHandler();


        expiryDateHandler.setNextHandler(shelfHandler);


        expiryDateHandler.handleMovingItemsToTheShelf(stockItems);


        System.setOut(System.out);


        String expectedOutput = "";
        assertEquals(expectedOutput, outputStream.toString());
    }
}

