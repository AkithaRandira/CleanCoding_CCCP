package core;

import Controller.CustomerController;
import View.CustomerView;

public class CustomerTaskInterface implements PointOfSales {
    @Override
    public void getInterface() {
        CustomerView view = new CustomerView();
        CustomerRepository customerRepository=new CustomerRepository(database.Database.getInstance());
        CustomerController controller = new CustomerController(view, customerRepository);

        view.setVisible(true);
    }
}
