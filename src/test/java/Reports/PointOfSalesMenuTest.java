package Reports;

import core.*;
import junit.framework.TestCase;

public class PointOfSalesMenuTest extends TestCase {

    private PointOfSalesMenu menu;

    public void setUp() throws Exception {
        super.setUp();
        menu = new PointOfSalesMenu();
    }

    public void tearDown() throws Exception {
        menu = null;
        super.tearDown();
    }

    public void testGetBillInterface() {
        PointOfSales billInterface = menu.getInterface(1);
        assertTrue(billInterface instanceof BillInterface);
    }

    public void testGetCustomerTaskInterface() {
        PointOfSales customerTaskInterface = menu.getInterface(2);
        assertTrue(customerTaskInterface instanceof CustomerTaskInterface);
    }

    public void testGetProductTaskInterface() {
        PointOfSales productTaskInterface = menu.getInterface(3);
        assertTrue(productTaskInterface instanceof ProductTaskInterface);
    }

    public void testGetStockInterface() {
        PointOfSales stockInterface = menu.getInterface(4);
        assertTrue(stockInterface instanceof StockInterface);
    }

    public void testGetItemsOnShelfInterface() {
        PointOfSales itemsOnShelfInterface = menu.getInterface(5);
        assertTrue(itemsOnShelfInterface instanceof ItemsOnShelfInterface);
    }

    public void testGetStockToShelfCheckInterface() {
        PointOfSales stockToShelfCheckInterface = menu.getInterface(6);
        assertTrue(stockToShelfCheckInterface instanceof StockToShelfCheckInterface);
    }

    public void testInvalidType() {
        PointOfSales invalidInterface = menu.getInterface(7);
        assertNull(invalidInterface);
    }
}

