package org.example;

public class PaymentContext {

    private PaymentStrategy paymentStrategy;

    public PaymentContext(PaymentStrategy paymentStrategy) {
        this.paymentStrategy = paymentStrategy;
    }

    public void executeStrategy( Bill bill){
        paymentStrategy.paymentMethod(bill);
    }
}
