package org.example;

import java.util.Date;
import java.util.List;

public class ConcreteBillBuilder implements BillBuilder {
    private Bill bill;

    public ConcreteBillBuilder() {
        this.bill = new Bill();
    }

    @Override
    public void addSerialNumber(Integer billSerialNumber) {
        bill.setBillSerialNumber(billSerialNumber);
    }

    @Override
    public void addBillItems(List<BillItem> billItems) {
        bill.setBillItems(billItems);
    }

    @Override
    public void addSubtotal(double subTotal) {
        bill.setSubTotal(subTotal);
    }

    @Override
    public void addDiscount(double discount) {
        bill.setDiscount(discount);
    }

    @Override
    public void addNetTotal(double netTotal) {
        bill.setNetTotal(netTotal);
    }

    @Override
    public void addCashTendered(double cashTendered) {
        bill.setCashTendered(cashTendered);
    }

    @Override
    public void addChangeAmount(double changeAmount) {
        bill.setChangeAmount(changeAmount);
    }

    @Override
    public void addDateOfBill(Date dateOfBill) {
        bill.setDateOfBill(dateOfBill);
    }

    @Override
    public void addTotalQuantitiesSold(int totalQuantitiesSold) {
        bill.setTotalQuantitiesSold(totalQuantitiesSold);
    }

    @Override
    public void addPaymentMethod(String paymentMethod) {

        bill.setPaymentMethod(paymentMethod);
    }

    @Override
    public void addCustomerName(String customerName) {
        bill.setCustomerName(customerName);

    }
    @Override
    public Bill getBill() {
        return this.bill;
    }
}
