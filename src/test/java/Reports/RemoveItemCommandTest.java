package Reports;

import core.*;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.*;

public class RemoveItemCommandTest {

    private BillDirector billDirector;
    private Item item;
    private RemoveItemCommand removeItemCommand;

    @Before
    public void setUp() {
        BillBuilder builder = new ConcreteBillBuilder();
        ItemDetailsService itemDetailsService = new ItemDetailsService(new ProductDetailsService());
        BillSaveService billSaveService = new BillSaveService();

        CheckStateService checkStateService = new CheckStateService(new StateContext());
        Map<String,PaymentStrategy> strategyMap=new HashMap<>();
        strategyMap.put("Cash",new CashPayment());
        strategyMap.put("Credit Card",new CreditCardPayment());
        BillGraphicalUnitInterfaceService billGraphicalUnitInterfaceService =new BillGraphicalUnitInterfaceService(strategyMap);
        billDirector = new BillDirector(builder, itemDetailsService, billSaveService, billGraphicalUnitInterfaceService, checkStateService);

//        Product product = new Product(1, "TestProduct");
//        item = new Item(4, "TestItem", 10.0, product);

        removeItemCommand = new RemoveItemCommand(billDirector, item, 2);
    }

    @After
    public void tearDown() {
        billDirector = null;
        item = null;
        removeItemCommand = null;
    }

    @Test
    public void testExecute() {
        billDirector.addItemToBill(item, 2);
        removeItemCommand.execute();
        assertEquals(0, billDirector.getBillItems().size());
        assertEquals(0.0, billDirector.getSubTotal(), 0.01);
    }

    @Test
    public void testUndo() {
        removeItemCommand.execute();
        removeItemCommand.undo();
        assertEquals(1, billDirector.getBillItems().size());
        assertEquals(20.0, billDirector.getSubTotal(), 0.01);
    }
}
