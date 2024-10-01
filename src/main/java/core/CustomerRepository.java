package core;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CustomerRepository {

    private final Database database;

    public CustomerRepository(Database database) {
        this.database = database;
    }

    public void addCustomer(Customer customer) {
        String SQL_INSERT = "INSERT INTO Customer (customerName,customertype) VALUES (?,?)";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setString(1, customer.getCustomerName());
            pstmt.setString(2, customer.getCustomerType());
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding customer to database: " + e.getMessage());
        }
    }

}
