package Reports;
import core.*;

import org.junit.Before;
import org.junit.Test;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.mock;

public class ConcreteBillBuilderTest {

    private ConcreteBillBuilder billBuilder;
    private Bill bill;

    @Before
    public void setUp() {
        billBuilder = new ConcreteBillBuilder();
        bill = new Bill();
    }

    @Test
    public void testAddSerialNumber() {
        int billSerialNumber = 12345;
        billBuilder.addSerialNumber(billSerialNumber);
        bill.setBillSerialNumber(billSerialNumber);
        assertEquals(bill.getBillSerialNumber(), billBuilder.getBill().getBillSerialNumber());
    }

    @Test
    public void testAddBillItems() {
        List<BillItem> billItems = mock(List.class);
        billBuilder.addBillItems(billItems);
        bill.setBillItems(billItems);
        assertEquals(bill.getBillItems(), billBuilder.getBill().getBillItems());
    }

    @Test
    public void testAddSubtotal() {
        double subTotal = 100.0;
        billBuilder.addSubtotal(subTotal);
        bill.setSubTotal(subTotal);
        assertEquals(bill.getSubTotal(), billBuilder.getBill().getSubTotal(), 0);
    }

    @Test
    public void testAddDiscount() {
        double discount = 10.0;
        billBuilder.addDiscount(discount);
        bill.setDiscount(discount);
        assertEquals(bill.getDiscount(), billBuilder.getBill().getDiscount(), 0);
    }

    @Test
    public void testAddNetTotal() {
        double netTotal = 90.0;
        billBuilder.addNetTotal(netTotal);
        bill.setNetTotal(netTotal);
        assertEquals(bill.getNetTotal(), billBuilder.getBill().getNetTotal(), 0);
    }

    @Test
    public void testAddCashTendered() {
        double cashTendered = 100.0;
        billBuilder.addCashTendered(cashTendered);
        bill.setCashTendered(cashTendered);
        assertEquals(bill.getCashTendered(), billBuilder.getBill().getCashTendered(), 0);
    }

    @Test
    public void testAddChangeAmount() {
        double changeAmount = 10.0;
        billBuilder.addChangeAmount(changeAmount);
        bill.setChangeAmount(changeAmount);
        assertEquals(bill.getChangeAmount(), billBuilder.getBill().getChangeAmount(), 0);
    }

    @Test
    public void testAddDateOfBill() {
        Date dateOfBill = new Date();
        billBuilder.addDateOfBill(dateOfBill);
        bill.setDateOfBill(dateOfBill);
        assertEquals(bill.getDateOfBill(), billBuilder.getBill().getDateOfBill());
    }

    @Test
    public void testAddTotalQuantitiesSold() {
        int totalQuantitiesSold = 5;
        billBuilder.addTotalQuantitiesSold(totalQuantitiesSold);
        bill.setTotalQuantitiesSold(totalQuantitiesSold);
        assertEquals(bill.getTotalQuantitiesSold(), billBuilder.getBill().getTotalQuantitiesSold());
    }

    @Test
    public void testAddPaymentMethod() {
        String paymentMethod = "Cash";
        billBuilder.addPaymentMethod(paymentMethod);
        bill.setPaymentMethod(paymentMethod);
        assertEquals(bill.getPaymentMethod(), billBuilder.getBill().getPaymentMethod());
    }

    @Test
    public void testAddCustomerName() {
        String customerName = "John Doe";
        billBuilder.addCustomerName(customerName);
        bill.setCustomerName(customerName);
        assertEquals(bill.getCustomerName(), billBuilder.getBill().getCustomerName());
    }
}
