package Reports;

import core.*;
import junit.framework.TestCase;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class StockObserverTest extends TestCase {

    @Mock
    private Stock stock;

    public void setUp() throws Exception {
        super.setUp();
        stock = mock(Stock.class);
    }

    public void tearDown() throws Exception {
    }

    public void testUpdate() {
        StockObserver observer = new StockObserver(stock);
        observer.update();
        verify(stock, times(1)).getQuantityInStock();
    }

    public void testLowStockAlert() {
        StockObserver observer = new StockObserver(stock);
        observer.lowStockAlert();
        verify(stock, times(1)).getQuantityInStock();
    }
}
