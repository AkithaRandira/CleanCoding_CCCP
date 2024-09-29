package org.example;

import java.util.Date;
import java.util.List;

public interface BillBuilder {

    void addSerialNumber(Integer billSerialNumber);
    void addBillItems(List<BillItem> billItems);
    void addSubtotal(double subTotal);
    void addDiscount(double discount);
    void addNetTotal(double netTotal);
    void addCashTendered(double cashTendered);
    void addChangeAmount(double changeAmount);
    void addDateOfBill(Date dateOfBill);
    void addTotalQuantitiesSold(int totalQuantitiesSold);

    void addPaymentMethod(String paymentMethod);
    void addCustomerName(String customerName);

    Bill getBill();
}
