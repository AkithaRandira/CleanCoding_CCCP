package Reports;

import core.*;
import junit.framework.TestCase;
import org.mockito.Mock;
import static org.mockito.Mockito.*;

public class ShelfObserverTest extends TestCase {

    @Mock
    private Item item;

    public void setUp() throws Exception {
        super.setUp();
        item = mock(Item.class);
    }

    public void tearDown() throws Exception {
    }

    public void testUpdate() {
        ShelfObserver observer = new ShelfObserver(item);
        observer.update();
        verify(item, times(1)).getQuantityOnShelf();
    }

    public void testLowStockAlert() {
        ShelfObserver observer = new ShelfObserver(item);
        observer.lowStockAlert();
        verify(item, times(1)).getQuantityOnShelf();
    }
}
