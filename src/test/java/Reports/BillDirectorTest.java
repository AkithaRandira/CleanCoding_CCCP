package Reports;
import core.*;

import core.BillDirector;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class BillDirectorTest {

    private BillDirector billDirector;
    private ItemDetailsService itemDetailsService;
    private BillGraphicalUnitInterfaceService billGraphicalUnitInterfaceService;

    @Before
    public void setUp() {
        BillBuilder builder = new ConcreteBillBuilder();
        itemDetailsService = new ItemDetailsService(new ProductDetailsService());
        BillSaveService billSaveService = new BillSaveService();
        Map<String, PaymentStrategy> strategyMap = new HashMap<>();
        strategyMap.put("Cash", new CashPayment());
        strategyMap.put("Credit Card", new CreditCardPayment());
        billGraphicalUnitInterfaceService = new BillGraphicalUnitInterfaceService(strategyMap);
        CheckStateService checkStateService = new CheckStateService(new StateContext());

        billDirector = new BillDirector(builder, itemDetailsService, billSaveService, billGraphicalUnitInterfaceService, checkStateService);
    }

    @After
    public void tearDown() {
        billDirector = null;
    }

    @Test
    public void testAddItem() {

        billGraphicalUnitInterfaceService.getTfItemCode().setText("FRP301002");
        billGraphicalUnitInterfaceService.getTfQuantity().setText("2");
        billDirector.addItem();
        assertEquals(1, billDirector.getBillItems().size());

        double subtotal = Double.parseDouble(billGraphicalUnitInterfaceService.getTfSubtotal().getText());
        assertEquals(200.0, subtotal, 0.01);
    }

//    @Test
//    public void testAddItemToBill() {
//        Product product = new Product(1, "TestProduct");
//        Item item = new Item(1001, "TestItem", 10.00, product);
//        billDirector.addItemToBill(item, 2);
//        assertEquals(1, billDirector.getBillItems().size());
//        double subtotal = Double.parseDouble(billGraphicalUnitInterfaceService.getTfSubtotal().getText());
//        assertEquals(20.0, subtotal,0.01);
//    }

//    @Test
//    public void testRemoveItemFromBill() {
//        Product product = new Product(1, "TestProduct");
//        Item item = new Item(1001, "TestItem", 10.0, product);
//        billDirector.addItemToBill(item, 2);
//        billDirector.removeItemFromBill(1001, 2);
//        assertEquals(0, billDirector.getBillItems().size());
//
//        double subtotal = Double.parseDouble(billGraphicalUnitInterfaceService.getTfSubtotal().getText());
//        // Correct the variable name to 'subTotal' instead of 'subTotal'
//        billGraphicalUnitInterfaceService.getTfSubtotal().setText(String.format("Subtotal: %.2f", subtotal));
//
//        assertEquals(0.0, subtotal, 0.01);
//    }

    @Test
    public void testFinalizeBill() {
        billGraphicalUnitInterfaceService.getTfItemCode().setText("FRP301001");
        billGraphicalUnitInterfaceService.getTfQuantity().setText("2");
        billDirector.addItem();

        //the bill serial number is the primary key
        billGraphicalUnitInterfaceService.getTfBillSerialNumber().setText("6965");
        billGraphicalUnitInterfaceService.getTfDiscount().setText("5");
        billGraphicalUnitInterfaceService.getTfCashTendered().setText("25");
        billGraphicalUnitInterfaceService.getPaymentStrategyComboBox().setSelectedItem("Cash");
        billGraphicalUnitInterfaceService.getCustomerName().setText("John Doe");

        billDirector.finalizeBill();

        // Verify the bill details
        List<BillItem> billItems = billDirector.getBillItems();
        assertEquals(1, billItems.size());

        // Get the first bill item
        BillItem billItem = billItems.get(0);
        assertEquals("Yoghurt", billItem.getItemName());
        assertEquals(2, billItem.getQuantity());
        assertEquals(100.00, billItem.getUnitPrice(), 0.01);
        assertEquals(200.00, billItem.getTotalPrice(), 0.01);

        // Verify the subtotal
        assertEquals(200.0, billDirector.getSubTotal(), 0.01);
    }


}
