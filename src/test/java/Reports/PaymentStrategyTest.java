package Reports;

import core.*;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class PaymentStrategyTest {

    @Test
    public void testCashPaymentMethod() {
        // Create a CashPayment instance
        PaymentStrategy paymentStrategy = new CashPayment();

        // Create a dummy bill
        Bill bill = new Bill();
        double initialAmount = 100.0;

        // Execute the payment method
        paymentStrategy.paymentMethod(bill);

        // Assert that the payment method sets the payment method correctly
        assertEquals("Cash", bill.getPaymentMethod());
    }

    @Test
    public void testCreditCardPaymentMethod() {
        // Create a CashPayment instance
        PaymentStrategy paymentStrategy = new CreditCardPayment();

        // Create a dummy bill
        Bill bill = new Bill();
        double initialAmount = 100.0;

        // Execute the payment method
        paymentStrategy.paymentMethod(bill);

        // Assert that the payment method sets the payment method correctly
        assertEquals("Credit Card", bill.getPaymentMethod());
    }
}

