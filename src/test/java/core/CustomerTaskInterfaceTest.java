package core;

import junit.framework.TestCase;



import Controller.CustomerController;
import View.CustomerView;
import database.Database;
import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class CustomerTaskInterfaceTest extends TestCase {

    private CustomerTaskInterface customerTaskInterface;
    private CustomerView mockView;
    private CustomerRepository mockCustomerRepository;
    private CustomerController mockCustomerController;
    private Database mockDatabase;

    protected void setUp() {
        customerTaskInterface = new CustomerTaskInterface();
        mockView = mock(CustomerView.class);
        mockCustomerRepository = mock(CustomerRepository.class);
        mockCustomerController = mock(CustomerController.class);
        mockDatabase = mock(Database.class);

        when(Database.getInstance()).thenReturn(mockDatabase);
//        when(mockCustomerRepository.getCustomer(anyInt())).thenReturn(null);
    }

    public void testGetInterface() {
        CustomerTaskInterface spyCustomerTaskInterface = spy(customerTaskInterface);

        doNothing().when(spyCustomerTaskInterface).getInterface();

        spyCustomerTaskInterface.getInterface();

        verify(mockView).setVisible(true);
    }

    public void testCustomerRepositoryInitialization() {
        customerTaskInterface.getInterface();

        assertNotNull("CustomerRepository should be initialized", new CustomerRepository(mockDatabase));
    }

    public void testControllerInitialization() {
        customerTaskInterface.getInterface();

        CustomerController controller = new CustomerController(mockView, mockCustomerRepository);
        assertNotNull("CustomerController should be initialized", controller);
    }
}
