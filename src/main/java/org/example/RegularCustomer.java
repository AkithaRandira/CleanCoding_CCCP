package org.example;

public class RegularCustomer extends Customer{
    public RegularCustomer(String customerFirstName, String customerLastName) {
        super(customerFirstName, customerLastName);
    }

    @Override
    public void setCustomerType() {
        this.customerType="Regular";
    }
}
