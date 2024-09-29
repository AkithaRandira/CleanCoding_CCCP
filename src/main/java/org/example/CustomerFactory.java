package org.example;

public class CustomerFactory {
    public static Customer createCustomer(String type,String firstName,String lastName) {
        switch (type.toLowerCase()) {
            case "regular":
                return new RegularCustomer(firstName,lastName);
            case "loyalty":
                return new LoyaltyCustomer(firstName,lastName);
            default:
                throw new IllegalArgumentException("Invalid customer type");
        }
    }
}
