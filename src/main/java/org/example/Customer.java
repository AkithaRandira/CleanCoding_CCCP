package org.example;

public abstract class Customer {

    private int customerID;
    private String customerName;
    protected String customerType;

    public Customer(String customerFirstName, String customerLastName) {
        this.customerName = customerFirstName + " " + customerLastName;
        setCustomerType();
    }
    public String getCustomerName() {
        return customerName;
    }

    public String getCustomerType() {
        return customerType;
    }

    public  abstract void setCustomerType();

}
