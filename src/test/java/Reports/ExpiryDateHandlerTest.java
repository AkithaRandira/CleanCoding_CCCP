package Reports;

import core.*;
import org.junit.Before;
import org.junit.Test;
import static org.mockito.Mockito.*;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class ExpiryDateHandlerTest {

    private ShelfHandler mockShelfHandler;
    private ExpiryDateHandler expiryDateHandler;

    @Before
    public void setUp() {
        mockShelfHandler = mock(ShelfHandler.class);
        expiryDateHandler = new ExpiryDateHandler();
        expiryDateHandler.setNextHandler(mockShelfHandler);
    }

    @Test
    public void testHandleMovingItemsToTheShelf() {
        // Create test data
        List<Stock> stocks = new ArrayList<>();
//        stocks.add(new Stock(1, "Item1", 123, 456, 100, Date.valueOf("2024-06-30"), Date.valueOf("2024-01-01"), Date.valueOf("2024-06-01")));
//        stocks.add(new Stock(2, "Item2", 456, 789, 80, Date.valueOf("2024-07-15"), Date.valueOf("2024-01-15"), Date.valueOf("2024-06-15")));

        // Invoke the method under test
        expiryDateHandler.handleMovingItemsToTheShelf(stocks);

        // Verify that the ShelfHandler's method was called with the correct data
        verify(mockShelfHandler).handleMovingItemsToTheShelf(anyList());
    }
}
