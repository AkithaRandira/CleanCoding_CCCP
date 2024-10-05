package Reports;

import core.Stock;
import core.StockIterator;
import junit.framework.TestCase;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class StockReportTest extends TestCase {

    private StockReport stockReport;
    private StockIterator mockStockIterator;
    private Stock mockStock;

    protected void setUp() {
        stockReport = new StockReport();
        mockStockIterator = mock(StockIterator.class);
        mockStock = mock(Stock.class);
    }

    public void testGetData() {
        List<Stock> mockStockList = new ArrayList<>();
        mockStockList.add(mockStock);

        StockIterator spyStockIterator = spy(new StockIterator(mockStockList));
        doNothing().when(spyStockIterator).loadStock();

        stockReport.getData();

        verify(spyStockIterator).loadStock();
        System.out.println("Verified that loadStock() is called in getData().");
    }

    public void testCreateReport() {
        when(mockStock.getBatchCode()).thenReturn("B001");
        when(mockStock.getItemCode()).thenReturn("A001");
        when(mockStock.getItemName()).thenReturn("Product A");
        when(mockStock.getQuantityInStock()).thenReturn(100);


        List<Stock> stocks = new ArrayList<>();
        stocks.add(mockStock);
        stockReport.stocks = stocks;

        stockReport.createReport();

        verify(mockStock, times(1)).getBatchCode();
        verify(mockStock, times(1)).getItemCode();
        verify(mockStock, times(1)).getItemName();
        verify(mockStock, times(1)).getQuantityInStock();
        verify(mockStock, times(1)).getExpiryDate();
        verify(mockStock, times(1)).getManufactureDate();
        verify(mockStock, times(1)).getBatchDate();

        System.out.println("Verified stock information is retrieved and printed in createReport().");
    }
}
