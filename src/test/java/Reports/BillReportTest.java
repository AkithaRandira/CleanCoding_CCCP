package Reports;

import core.Bill;
import core.BillItem;
import core.BillIterator;
import core.BillRepository;
import junit.framework.TestCase;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class BillReportTest extends TestCase {

    private BillReport billReport;
    private BillRepository mockBillRepository;
    private BillIterator mockBillIterator;
    private Bill mockBill;

    protected void setUp() {
        billReport = new BillReport();
        mockBillRepository = mock(BillRepository.class);
        mockBillIterator = mock(BillIterator.class);
        mockBill = mock(Bill.class);
    }

    public void testGetData() {
        List<Bill> mockBillList = new ArrayList<>();
        mockBillList.add(mockBill);

        BillIterator spyBillIterator = spy(new BillIterator(mockBillList, mockBillRepository));
        doNothing().when(spyBillIterator).loadBills();

        billReport.getData();

        verify(spyBillIterator).loadBills();
    }

    public void testCreateReport() {
        List<BillItem> billItems = new ArrayList<>();
        BillItem mockBillItem = new BillItem("A001", "Item Name", 2, 100.0, 200.0, null);
        billItems.add(mockBillItem);

        when(mockBill.getBillSerialNumber()).thenReturn(123);
        when(mockBill.getNetTotal()).thenReturn(1000.0);
        when(mockBill.getDiscount()).thenReturn(50.0);
        when(mockBill.getCashTendered()).thenReturn(1050.0);
        when(mockBill.getChangeAmount()).thenReturn(50.0);
        when(mockBill.getSubTotal()).thenReturn(950.0);
        when(mockBill.getTotalQuantitiesSold()).thenReturn(5);
        when(mockBill.getCustomerName()).thenReturn("John Doe");
        when(mockBill.getBillItems()).thenReturn(billItems);

        List<Bill> bills = new ArrayList<>();
        bills.add(mockBill);


        billReport.createReport();

        verify(mockBill, times(1)).getBillSerialNumber();
        verify(mockBill, times(1)).getNetTotal();
        verify(mockBill, times(1)).getDiscount();
        verify(mockBill, times(1)).getCashTendered();
        verify(mockBill, times(1)).getChangeAmount();
        verify(mockBill, times(1)).getSubTotal();
        verify(mockBill, times(1)).getDateOfBill();
        verify(mockBill, times(1)).getTotalQuantitiesSold();
        verify(mockBill, times(1)).getCustomerName();
        verify(mockBill, times(1)).getBillItems();
    }
}
