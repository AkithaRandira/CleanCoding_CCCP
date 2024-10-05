package Reports;

import core.Stock;
import core.StockIterator;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;
import static org.mockito.Mockito.*;

public class ReOrderReportTest extends TestCase {

    private ReOrderReport reOrderReport;
    private StockIterator mockStockIterator;
    private Stock mockStock;

    protected void setUp() {
        reOrderReport = new ReOrderReport();
        mockStockIterator = mock(StockIterator.class);
        mockStock = mock(Stock.class);
    }

    public void testGetData() {
        List<Stock> mockStockList = new ArrayList<>();
        mockStockList.add(mockStock);

        StockIterator spyStockIterator = spy(new StockIterator(mockStockList));
        doNothing().when(spyStockIterator).reOrderStockLevels();

        reOrderReport.getData();

        verify(spyStockIterator).reOrderStockLevels();
        System.out.println("Verified that reOrderStockLevels() is called in getData().");
    }

    public void testCreateReport() {
        when(mockStock.getBatchCode()).thenReturn("B001");
        when(mockStock.getItemName()).thenReturn("Item A");
        when(mockStock.getItemCode()).thenReturn("A001");
        when(mockStock.getQuantityInStock()).thenReturn(100);

        List<Stock> stocks = new ArrayList<>();
        stocks.add(mockStock);
        reOrderReport.stocks = stocks;

        reOrderReport.createReport();

        verify(mockStock, times(1)).getBatchCode();
        verify(mockStock, times(1)).getItemName();
        verify(mockStock, times(1)).getItemCode();
        verify(mockStock, times(1)).getQuantityInStock();

        System.out.println("Verified stock information is retrieved and printed in createReport().");
    }
}
