package core;

import junit.framework.TestCase;


import database.Database;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.mockito.Mockito.*;

public class BillSaveServiceTest extends TestCase {

    private BillSaveService billSaveService;
    private Connection mockConnection;
    private PreparedStatement mockBillStatement;
    private PreparedStatement mockBillItemStatement;
    private PreparedStatement mockUpdateItemStatement;

    protected void setUp() throws Exception {
        billSaveService = new BillSaveService();
        mockConnection = mock(Connection.class);
        mockBillStatement = mock(PreparedStatement.class);
        mockBillItemStatement = mock(PreparedStatement.class);
        mockUpdateItemStatement = mock(PreparedStatement.class);

        when(Database.connect()).thenReturn(mockConnection);
        when(mockConnection.prepareStatement(anyString())).thenReturn(mockBillStatement, mockBillItemStatement, mockUpdateItemStatement);
    }

    public void testSaveBillSuccess() throws Exception {
        Bill mockBill = createMockBill();

        doNothing().when(mockConnection).setAutoCommit(false);
        doNothing().when(mockBillStatement).executeUpdate();
        doNothing().when(mockBillItemStatement).executeUpdate();
        doNothing().when(mockUpdateItemStatement).executeUpdate();
        doNothing().when(mockConnection).commit();

        billSaveService.saveBill(mockBill);

        verify(mockBillStatement).setInt(1, mockBill.getBillSerialNumber());
        verify(mockBillStatement).setDate(2, new java.sql.Date(mockBill.getDateOfBill().getTime()));
        verify(mockBillStatement).setDouble(3, mockBill.getSubTotal());
        verify(mockBillStatement).setDouble(4, mockBill.getDiscount());
        verify(mockBillStatement).setDouble(5, mockBill.getNetTotal());
        verify(mockBillStatement).setDouble(6, mockBill.getCashTendered());
        verify(mockBillStatement).setDouble(7, mockBill.getChangeAmount());
        verify(mockBillStatement).setInt(8, mockBill.getTotalQuantitiesSold());
        verify(mockBillStatement).setString(9, mockBill.getPaymentMethod());
        verify(mockBillStatement).setString(10, mockBill.getCustomerName());

        verify(mockBillItemStatement, times(mockBill.getBillItems().size())).executeUpdate();
        verify(mockUpdateItemStatement, times(mockBill.getBillItems().size())).executeUpdate();
        verify(mockConnection).commit();
    }

    public void testSaveBillSQLException() throws Exception {
        Bill mockBill = createMockBill();

        doNothing().when(mockConnection).setAutoCommit(false);
        when(mockBillStatement.executeUpdate()).thenThrow(new SQLException("Insert Bill Error"));

        try {
            billSaveService.saveBill(mockBill);
            fail("Expected SQLException");
        } catch (Exception e) {
            verify(mockConnection).rollback();
        }
    }

    private Bill createMockBill() {
        List<BillItem> items = new ArrayList<>();
        items.add(new BillItem("101", "Sample Item", 2, 100.0, 200.0, null));

        return new Bill(1, 950.0, items, 50.0, 1050.0, 100.0, 1000.0, new Date(), 10, "Cash", "John Doe");
    }

    public void testRollbackOnSQLException() throws Exception {
        Bill mockBill = createMockBill();

        doNothing().when(mockConnection).setAutoCommit(false);
        when(mockBillStatement.executeUpdate()).thenThrow(new SQLException("Insert Bill Error"));
//        when(mockConnection.rollback()).thenReturn(null);

        try {
            billSaveService.saveBill(mockBill);
        } catch (Exception e) {
            verify(mockConnection).rollback();
        }
    }
}
