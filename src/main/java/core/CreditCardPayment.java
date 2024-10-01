package core;

public class CreditCardPayment implements PaymentStrategy{

    @Override
    public void paymentMethod(Bill bill) {
        bill.setPaymentMethod("Credit Card");
    }
}
