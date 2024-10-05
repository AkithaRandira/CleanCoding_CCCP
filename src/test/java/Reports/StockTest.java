
package Reports;
import core.*;

import org.junit.Test;
import static org.junit.Assert.*;

public class StockTest {

    private Item item;

    @Test
    public void testStockGettersAndSetters() {

        Stock stock = new Stock();
        Item item = new Item();

        stock.setItemName("Test Item");
        stock.setItemCode(String.valueOf(123));
        stock.setBatchCode(String.valueOf(456));
        stock.setQuantityInStock(100);


        java.sql.Date currentDate = new java.sql.Date(System.currentTimeMillis());
        stock.setExpiryDate(currentDate);
        stock.setManufactureDate(currentDate);
        stock.setBatchDate(currentDate);


        assertEquals("Test Item", stock.getItemName());
        assertEquals(Integer.valueOf(123), stock.getItemCode());
        assertEquals(Integer.valueOf(456), stock.getBatchCode());
        assertEquals(Integer.valueOf(100), stock.getQuantityInStock());
        assertEquals(currentDate, stock.getExpiryDate());
        assertEquals(currentDate, stock.getManufactureDate());
        assertEquals(currentDate, stock.getBatchDate());
    }

    @Test
    public void testCheckLowStock() {

        Stock stock = new Stock();
        stock.setQuantityInStock(40);


        MockObserver observer = new MockObserver();
        stock.attach(observer);


        stock.checkLowStock();


        assertTrue(observer.wasUpdateCalled());
        assertTrue(observer.wasLowStockAlertCalled());
    }


    class MockObserver extends Observer {
        private boolean updateCalled = false;
        private boolean lowStockAlertCalled = false;

        @Override
        public void update() {
            updateCalled = true;
        }

        @Override
        public void lowStockAlert() {
            lowStockAlertCalled = true;
        }

        public boolean wasUpdateCalled() {
            return updateCalled;
        }

        public boolean wasLowStockAlertCalled() {
            return lowStockAlertCalled;
        }
    }
}