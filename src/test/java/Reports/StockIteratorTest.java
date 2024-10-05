package Reports;

import core.*;
import org.junit.Before;
import org.junit.Test;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class StockIteratorTest {

    private StockIterator stockIterator;

    @Before
    public void setUp() {
        // Create a list of stocks to test the iterator
//        List<Stock> stocks = new ArrayList<>();
//        stocks.add(new Stock(1, "Test Item 1", 123, 456, 100, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));
//        stocks.add(new Stock(2, "Test Item 2", 789, 101112, 200, new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis()), new Date(System.currentTimeMillis())));

        // Create the StockIterator with the list of stocks
//        stockIterator = new StockIterator(stocks);
    }

    @Test
    public void testHasNext() {
        assertTrue(stockIterator.hasNext());
        stockIterator.next();
        assertTrue(stockIterator.hasNext());
        stockIterator.next();
        assertFalse(stockIterator.hasNext());
    }

    @Test
    public void testNext() {
        // Test if next() returns the correct stock items
        Stock stock1 = (Stock) stockIterator.next();
        assertEquals("Test Item 1", stock1.getItemName());
        Stock stock2 = (Stock) stockIterator.next();
        assertEquals("Test Item 2", stock2.getItemName());

        // Test if next() returns null when there are no more items
        assertNull(stockIterator.next());
    }

    @Test
    public void testLoadStock() {
        stockIterator.loadStock();
        assertTrue(stockIterator.hasNext());
    }

    @Test
    public void testReOrderStockLevels() {
        stockIterator.reOrderStockLevels();
        assertTrue(stockIterator.hasNext());
    }
}
