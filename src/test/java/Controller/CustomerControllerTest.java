package Controller;

import database.Database;
import core.*;
import View.CustomerView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

public class CustomerControllerTest {

    private CustomerView customerView;
    private CustomerRepository customerRepository;
    private CustomerController customerController;
    private Database database;

    @Before
    public void setUp() throws SQLException {
        customerView = mock(CustomerView.class);
        database = new Database(); // Use actual database
        customerRepository = new CustomerRepository(database);
        customerController = new CustomerController(customerView, customerRepository);


        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Customer WHERE customerName LIKE 'John Doe%'")) {
            statement.executeUpdate();
        }
    }

    @After
    public void tearDown() throws SQLException {

        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM Customer WHERE customerName LIKE 'John Doe%'")) {
            statement.executeUpdate();
        }
    }

    @Test
    public void testCustomerCreationAndAdd() throws SQLException {

        when(customerView.getCustomerFirstName()).thenReturn("John");
        when(customerView.getCustomerLastName()).thenReturn("Doe");
        when(customerView.getCustomerType()).thenReturn("Regular");


        customerController.new CustomerListener().actionPerformed(null);


        verify(customerView, times(1)).setCustomerName("John Doe");
        verify(customerView, times(1)).displaySuccessMessage("Customer added successfully!");


        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM Customer WHERE customerName = 'John Doe' AND customerType = 'Regular'")) {
            boolean hasResults = statement.executeQuery().next();
            assertTrue("Customer should be added to the database", hasResults);
        }
    }

    @Test
    public void testInvalidCustomerType() {

        when(customerView.getCustomerFirstName()).thenReturn("John");
        when(customerView.getCustomerLastName()).thenReturn("Doe");
        when(customerView.getCustomerType()).thenReturn("InvalidType");


        customerController.new CustomerListener().actionPerformed(null);


        verify(customerView, times(1)).displayError("Error: Invalid customer type");
    }
}
