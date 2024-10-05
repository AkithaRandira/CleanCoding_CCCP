
package Reports;
import core.*;
import core.BillDirector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class AddItemCommandTest {

    private BillDirector billDirector;
    private Item item;
    private AddItemCommand addItemCommand;
    @Before
    public void setUp() {
        BillBuilder builder = new ConcreteBillBuilder();
        ItemDetailsService itemDetailsService = new ItemDetailsService(new ProductDetailsService());
        BillSaveService billSaveService = new BillSaveService();
        Map<String,PaymentStrategy> strategyMap=new HashMap<>();
        strategyMap.put("Cash",new CashPayment());
        strategyMap.put("Credit Card",new CreditCardPayment());
        BillGraphicalUnitInterfaceService billGraphicalUnitInterfaceService =new BillGraphicalUnitInterfaceService(strategyMap);

        CheckStateService checkStateService = new CheckStateService(new StateContext());

        billDirector = new BillDirector(builder, itemDetailsService, billSaveService, billGraphicalUnitInterfaceService, checkStateService);

//        Product product = new Product(1, "TestProduct");
//        item = new Item(1001, "TestItem", 10.0, product);

        addItemCommand = new AddItemCommand(billDirector, item, 2);
    }

    @After
    public void tearDown() {
        billDirector = null;
        item = null;
        addItemCommand = null;
    }

    @Test
    public void testExecute() {
        addItemCommand.execute();
        assertEquals(1, billDirector.getBillItems().size());

        assertEquals(20.0,billDirector.getSubTotal(), 0.01);
    }

    @Test
    public void testUndo() {
        addItemCommand.execute();
        addItemCommand.undo();
        assertEquals(0, billDirector.getBillItems().size());
        assertEquals(0.0, billDirector.getSubTotal(), 0.01);
    }
}
