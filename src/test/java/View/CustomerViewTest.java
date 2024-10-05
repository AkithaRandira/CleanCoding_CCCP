package View;

import core.Customer;
import junit.framework.TestCase;
import javax.swing.*;
import java.awt.event.ActionListener;
import static org.mockito.Mockito.*;

public class CustomerViewTest extends TestCase {

    private CustomerView customerView;

    protected void setUp() {
        customerView = new CustomerView();
    }

    public void testGetCustomerFirstName() {
        JTextField mockFirstNameField = mock(JTextField.class);
        when(mockFirstNameField.getText()).thenReturn("John");


        String firstName = customerView.getCustomerFirstName();
        assertEquals("Customer first name should be 'John'", "John", firstName);
    }

    public void testGetCustomerLastName() {
        JTextField mockLastNameField = mock(JTextField.class);
        when(mockLastNameField.getText()).thenReturn("Doe");


        String lastName = customerView.getCustomerLastName();
        assertEquals("Customer last name should be 'Doe'", "Doe", lastName);
    }

    public void testGetCustomerType() {
        JComboBox<String> mockComboBox = mock(JComboBox.class);
        when(mockComboBox.getSelectedItem()).thenReturn("Loyalty");


        String customerType = customerView.getCustomerType();
        assertEquals("Customer type should be 'Loyalty'", "Loyalty", customerType);
    }

    public void testSetCustomerType() {
        JComboBox<String> mockComboBox = mock(JComboBox.class);


        customerView.setCustomerType("Regular");
        verify(mockComboBox).setSelectedItem("Regular");
    }

    public void testSetCustomerName() {
        JTextField mockCustomerNameField = mock(JTextField.class);


        customerView.setCustomerName("John Doe");
        verify(mockCustomerNameField).setText("John Doe");
    }

    public void testAddCustomerListener() {
        ActionListener mockListener = mock(ActionListener.class);
        JButton mockAddButton = mock(JButton.class);

        customerView.addCustomerListener(mockListener);
        verify(mockAddButton).addActionListener(mockListener);
    }

    public void testDisplaySuccessMessage() {
        JOptionPane mockPane = mock(JOptionPane.class);
        customerView.displaySuccessMessage("Customer added successfully");
        mockPane.showMessageDialog(customerView, "Customer added successfully");
    }

    public void testDisplayError() {
        JOptionPane mockPane = mock(JOptionPane.class);
        customerView.displayError("Error adding customer");
        mockPane.showMessageDialog(customerView, "Error adding customer");
    }
}
