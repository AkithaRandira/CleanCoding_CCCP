package Controller;

import org.example.*;
import View.CustomerView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;


public class CustomerController {
    private CustomerView theView;
    private CustomerRepository customerRepository;

    public CustomerController(CustomerView theView,CustomerRepository customerRepository) {
        this.theView = theView;
        this.customerRepository=customerRepository;

        this.theView.addCustomerListener(new CustomerListener());

    }

    class CustomerListener implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
            String customerFirstName;
            String customerLastName;
            String customerType;

            try {
                customerFirstName = theView.getCustomerFirstName();
                customerLastName = theView.getCustomerLastName();
                customerType=theView.getCustomerType();

                Customer theModel = CustomerFactory.createCustomer(customerType,customerFirstName,customerLastName);
                customerRepository.addCustomer(theModel);
                theView.setCustomerName(theModel.getCustomerName());
                theView.displaySuccessMessage("Customer added successfully!");
            } catch (Exception exception) {
                theView.displayError("Error: " + exception.getMessage());
            }
        }
    }
}

