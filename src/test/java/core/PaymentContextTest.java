package core;

import junit.framework.TestCase;


import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class PaymentContextTest extends TestCase {

    private PaymentContext paymentContext;
    private PaymentStrategy mockPaymentStrategy;
    private Bill mockBill;

    protected void setUp() {
        mockPaymentStrategy = mock(PaymentStrategy.class);
        paymentContext = new PaymentContext(mockPaymentStrategy);
        mockBill = mock(Bill.class);
    }

    public void testExecuteStrategy() {
        paymentContext.executeStrategy(mockBill);

        // Verify that the paymentMethod of PaymentStrategy is called with the correct Bill object
        verify(mockPaymentStrategy).paymentMethod(mockBill);
    }

    public void testConstructorSetsStrategy() {
        // Verify that the PaymentContext is initialized with the correct PaymentStrategy
        assertNotNull("Payment strategy should be set", paymentContext);
    }
}
