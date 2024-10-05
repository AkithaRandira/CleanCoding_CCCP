package core;

import junit.framework.TestCase;


import junit.framework.TestCase;

public class BillItemTest extends TestCase {

    private BillItem billItem;
    private Item mockItem;

    protected void setUp() {
        mockItem = new Item();
        billItem = new BillItem("A001", "Sample Item", 5, 100.0, 500.0, mockItem);
    }

    public void testBillItemConstructorWithAllParameters() {
        assertEquals("Item code should be A001", "A001", billItem.getItemCode());
        assertEquals("Item name should be Sample Item", "Sample Item", billItem.getItemName());
        assertEquals("Quantity should be 5", 5, billItem.getQuantity());
        assertEquals("Unit price should be 100.0", 100.0, billItem.getUnitPrice());
        assertEquals("Total price should be 500.0", 500.0, billItem.getTotalPrice());
        assertSame("Item object should be the same", mockItem, billItem.getItem());
    }

    public void testBillItemConstructorWithoutItem() {
        BillItem newItem = new BillItem("A002", 10, 50.0, 500.0);
        assertEquals("Item code should be A002", "A002", newItem.getItemCode());
        assertEquals("Quantity should be 10", 10, newItem.getQuantity());
        assertEquals("Unit price should be 50.0", 50.0, newItem.getUnitPrice());
        assertEquals("Total price should be 500.0", 500.0, newItem.getTotalPrice());
        assertNull("Item should be null", newItem.getItem());
    }

    public void testSetAndGetItemCode() {
        billItem.setItemCode("B001");
        assertEquals("Item code should be B001", "B001", billItem.getItemCode());
    }

    public void testSetAndGetItemName() {
        billItem.setItemName("New Item");
        assertEquals("Item name should be New Item", "New Item", billItem.getItemName());
    }

    public void testSetAndGetQuantity() {
        billItem.setQuantity(8);
        assertEquals("Quantity should be 8", 8, billItem.getQuantity());
        assertEquals("Total price should be updated to 800.0", 800.0, billItem.getTotalPrice());
    }

    public void testSetAndGetUnitPrice() {
        billItem.setUnitPrice(120.0);
        assertEquals("Unit price should be 120.0", 120.0, billItem.getUnitPrice());
        assertEquals("Total price should be updated to 600.0", 600.0, billItem.getTotalPrice());
    }

    public void testSetAndGetTotalPrice() {
        billItem.setTotalPrice(1000.0);
        assertEquals("Total price should be 1000.0", 1000.0, billItem.getTotalPrice());
    }

    public void testSetAndGetItemDescription() {
        billItem.setItemDescription("Sample Description");
        assertEquals("Item description should be 'Sample Description'", "Sample Description", billItem.getItemDescription());
    }

    public void testSetAndGetItem() {
        Item newItem = new Item();
        billItem.setItem(newItem);
        assertSame("Item should be the same as newItem", newItem, billItem.getItem());
    }
}
