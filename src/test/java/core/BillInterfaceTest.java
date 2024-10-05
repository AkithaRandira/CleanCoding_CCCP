package core;

import junit.framework.TestCase;



import junit.framework.TestCase;
import java.util.Map;

import static org.mockito.Mockito.*;

public class BillInterfaceTest extends TestCase {

    private BillInterface billInterface;
    private BillBuilder mockBuilder;
    private ProductDetailsService mockProductDetailsService;
    private ItemDetailsService mockItemDetailsService;
    private BillSaveService mockBillSaveService;
    private BillGraphicalUnitInterfaceService mockBillGUI;
    private CheckStateService mockCheckStateService;
    private BillDirector mockBillDirector;
    private Map<String, PaymentStrategy> mockStrategyMap;

    protected void setUp() {
        billInterface = new BillInterface();

        mockBuilder = mock(BillBuilder.class);
        mockProductDetailsService = mock(ProductDetailsService.class);
        mockItemDetailsService = mock(ItemDetailsService.class);
        mockBillSaveService = mock(BillSaveService.class);
        mockBillGUI = mock(BillGraphicalUnitInterfaceService.class);
        mockCheckStateService = mock(CheckStateService.class);
        mockBillDirector = mock(BillDirector.class);
        mockStrategyMap = mock(Map.class);
    }

    public void testGetInterface() {
        BillBuilder mockBuilder = mock(ConcreteBillBuilder.class);
        ProductDetailsService mockProductDetailsService = mock(ProductDetailsService.class);
        ItemDetailsService mockItemDetailsService = new ItemDetailsService(mockProductDetailsService);
        BillSaveService mockBillSaveService = mock(BillSaveService.class);
        Map<String, PaymentStrategy> strategyMap = mock(Map.class);
        strategyMap.put("Cash", new CashPayment());
        strategyMap.put("Credit Card", new CreditCardPayment());
        BillGraphicalUnitInterfaceService mockBillGUI = new BillGraphicalUnitInterfaceService(strategyMap);
        CheckStateService mockCheckStateService = new CheckStateService(new StateContext());

        billInterface.getInterface();

        assertNotNull(mockBuilder);
        assertNotNull(mockProductDetailsService);
        assertNotNull(mockItemDetailsService);
        assertNotNull(mockBillSaveService);
        assertNotNull(mockBillGUI);
        assertNotNull(mockCheckStateService);

        verify(strategyMap).put("Cash", new CashPayment());
        verify(strategyMap).put("Credit Card", new CreditCardPayment());
    }
}
