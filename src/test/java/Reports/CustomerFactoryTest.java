package Reports;
import core.*;

import junit.framework.TestCase;

public class CustomerFactoryTest extends TestCase {

    public void testCreateRegularCustomer() {
        Customer regularCustomer = CustomerFactory.createCustomer("Regular", "John", "Doe");
        assertNotNull(regularCustomer);
        assertEquals("John Doe", regularCustomer.getCustomerName());
        assertEquals("Regular", regularCustomer.getCustomerType());
    }

    public void testCreateLoyaltyCustomer() {
        Customer loyaltyCustomer = CustomerFactory.createCustomer("Loyalty", "Jane", "Smith");
        assertNotNull(loyaltyCustomer);
        assertEquals("Jane Smith", loyaltyCustomer.getCustomerName());
        assertEquals("Loyalty", loyaltyCustomer.getCustomerType());
    }

    public void testCreateCustomerWithInvalidType() {
        try {
            Customer invalidCustomer = CustomerFactory.createCustomer("InvalidType", "Alice", "Johnson");
            fail("Expected an IllegalArgumentException to be thrown");
        } catch (IllegalArgumentException e) {
            assertEquals("Invalid customer type", e.getMessage());
        }

    }
}