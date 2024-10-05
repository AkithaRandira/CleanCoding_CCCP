package Reports;


import core.*;
import junit.framework.TestCase;

public class LoyaltyCustomerTest extends TestCase {

    private LoyaltyCustomer loyaltyCustomer;

    public void setUp() throws Exception {
        super.setUp();
        loyaltyCustomer = new LoyaltyCustomer("John", "Doe");
    }

    public void tearDown() throws Exception {
    }

    public void testGetCustomerName() {
        assertEquals("John Doe", loyaltyCustomer.getCustomerName());
    }

    public void testGetCustomerType() {
        assertEquals("Loyalty", loyaltyCustomer.getCustomerType());
    }

    public void testSetCustomerType() {
        loyaltyCustomer.setCustomerType();
        assertEquals("Loyalty", loyaltyCustomer.getCustomerType());
    }
}

