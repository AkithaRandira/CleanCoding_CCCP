package org.example;

public class CashPayment implements PaymentStrategy{
    @Override
    public void paymentMethod(Bill bill) {
        bill.setPaymentMethod("Cash");
    }
}
