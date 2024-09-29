package org.example;

public class LoyaltyCustomer extends Customer{

    public LoyaltyCustomer(String  customerFirstName, String customerLastName){
        super(customerFirstName,customerLastName);

    }
    @Override
    public void setCustomerType() {
        this.customerType="Loyalty";
    }
}
