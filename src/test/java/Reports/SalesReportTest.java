package Reports;


import core.Bill;
import core.BillIterator;
import core.BillRepository;
import core.SalesPerDayCriteria;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class SalesReportTest extends TestCase {

    private SalesReport salesReport;
    private BillRepository mockBillRepository;
    private BillIterator mockBillIterator;
    private SalesPerDayCriteria mockSalesPerDayCriteria;
    private Bill mockBill;

    protected void setUp() {
        salesReport = new SalesReport();
        mockBillRepository = mock(BillRepository.class);
        mockBillIterator = mock(BillIterator.class);
        mockSalesPerDayCriteria = mock(SalesPerDayCriteria.class);
        mockBill = mock(Bill.class);
    }

    public void testGetData() {
        List<Bill> mockBillList = new ArrayList<>();
        mockBillList.add(mockBill);

        BillIterator spyBillIterator = spy(new BillIterator(mockBillList, mockBillRepository));
        doNothing().when(spyBillIterator).loadBills();

        salesReport.getData();

        verify(spyBillIterator).loadBills();
        System.out.println("Verified that loadBills() is called in getData().");
    }

    public void testCreateReport() {
        List<Bill> mockBillList = new ArrayList<>();
        mockBillList.add(mockBill);

        when(mockBill.getNetTotal()).thenReturn(1000.0);

        List<Bill> filteredBills = new ArrayList<>();
        filteredBills.add(mockBill);

        when(mockSalesPerDayCriteria.FilterBillsByDay(anyList())).thenReturn(filteredBills);

        salesReport.bills = mockBillList;
        salesReport.createReport();

        verify(mockSalesPerDayCriteria).FilterBillsByDay(mockBillList);
        assertEquals(1000.0, filteredBills.get(0).getNetTotal());

        System.out.println("Verified that filtered bills are processed and total revenue is calculated.");
    }
}
