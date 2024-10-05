package core;

import junit.framework.TestCase;



import database.Database;
import junit.framework.TestCase;
import java.sql.*;
import java.util.List;
import static org.mockito.Mockito.*;

public class BillRepositoryTest extends TestCase {

    private BillRepository billRepository;
    private Connection mockConnection;
    private Statement mockStatement;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;
    private ResultSet mockItemResultSet;

    protected void setUp() {
        billRepository = new BillRepository();
        mockConnection = mock(Connection.class);
        mockStatement = mock(Statement.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);
        mockItemResultSet = mock(ResultSet.class);
    }

    public void testLoadAllBills() throws Exception {
        when(Database.connect()).thenReturn(mockConnection);
        when(mockConnection.createStatement()).thenReturn(mockStatement);
        when(mockStatement.executeQuery("SELECT * FROM bill")).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true).thenReturn(false);
        when(mockResultSet.getInt("billserialnumber")).thenReturn(1);
        when(mockResultSet.getDouble("nettotal")).thenReturn(1000.0);
        when(mockResultSet.getDouble("discount")).thenReturn(50.0);
        when(mockResultSet.getDouble("cashtendered")).thenReturn(1050.0);
        when(mockResultSet.getDouble("changeamount")).thenReturn(50.0);
        when(mockResultSet.getDouble("subtotal")).thenReturn(950.0);
        when(mockResultSet.getDate("dateofbill")).thenReturn(new Date(2024, 10, 4));
        when(mockResultSet.getInt("totalquantitiessold")).thenReturn(10);
        when(mockResultSet.getString("paymentmethod")).thenReturn("Cash");
        when(mockResultSet.getString("customername")).thenReturn("John Doe");

        when(mockConnection.prepareStatement("SELECT * FROM billitem WHERE billserialnumber = ?")).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockItemResultSet);

        when(mockItemResultSet.next()).thenReturn(true).thenReturn(false); // Mock 1 item
        when(mockItemResultSet.getInt("itemcode")).thenReturn(101);
        when(mockItemResultSet.getInt("qtyperitem")).thenReturn(2);
        when(mockItemResultSet.getDouble("priceperitem")).thenReturn(500.0);
        when(mockItemResultSet.getDouble("totalamount")).thenReturn(1000.0);

        List<Bill> bills = billRepository.loadAllBills();

        assertEquals("There should be 1 bill loaded", 1, bills.size());
        Bill bill = bills.get(0);
        assertEquals("Bill serial number should be 1", 1, bill.getBillSerialNumber());
        assertEquals("Net total should be 1000.0", 1000.0, bill.getNetTotal());
        assertEquals("Discount should be 50.0", 50.0, bill.getDiscount());
        assertEquals("Cash tendered should be 1050.0", 1050.0, bill.getCashTendered());
        assertEquals("Change amount should be 50.0", 50.0, bill.getChangeAmount());
        assertEquals("Sub total should be 950.0", 950.0, bill.getSubTotal());
        assertEquals("Total quantities sold should be 10", 10, bill.getTotalQuantitiesSold());
//        assertEquals("Payment strategy should be Cash", "Cash", bill.getPaymentStrategy());
        assertEquals("Customer name should be John Doe", "John Doe", bill.getCustomerName());

        List<BillItem> items = bill.getBillItems();
        assertEquals("There should be 1 item in the bill", 1, items.size());
        BillItem item = items.get(0);
        assertEquals("Item code should be 101", "101", item.getItemCode());
        assertEquals("Item quantity should be 2", 2, item.getQuantity());
        assertEquals("Item unit price should be 500.0", 500.0, item.getUnitPrice());
        assertEquals("Item total price should be 1000.0", 1000.0, item.getTotalPrice());
    }

    public void testLoadAllBillsSQLException() throws Exception {
        when(Database.connect()).thenThrow(new SQLException("Database error"));
        try {
            billRepository.loadAllBills();
            fail("Should have thrown RuntimeException");
        } catch (RuntimeException e) {
            assertTrue(e.getMessage().contains("Database error"));
        }
    }
}
