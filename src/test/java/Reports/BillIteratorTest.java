package Reports;
import core.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

public class BillIteratorTest {

    private BillIterator billIterator;
    private BillRepository billRepositoryMock;
    private List<Bill> bills;

    @Before
    public void setUp() {
        bills = new ArrayList<>();
        billRepositoryMock = mock(BillRepository.class);
        billIterator = new BillIterator(bills, billRepositoryMock);
    }

    @After
    public void tearDown() {
        billIterator = null;
        billRepositoryMock = null;
        bills = null;
    }

    @Test
    public void testLoadBills() {
        List<Bill> mockBills = new ArrayList<>();
        when(billRepositoryMock.loadAllBills()).thenReturn(mockBills);

        billIterator.loadBills();

        assertEquals(mockBills, bills);
        verify(billRepositoryMock).loadAllBills();
    }

    @Test
    public void testHasNext() {
        bills.add(new Bill());
        billIterator.loadBills(); // Ensure bills are loaded

        assertTrue(billIterator.hasNext());
    }

    @Test
    public void testNext() {
        Bill bill = new Bill();
        bills.add(bill);
        billIterator.loadBills(); // Ensure bills are loaded

        assertEquals(bill, billIterator.next());
    }

    @Test
    public void testHasNextWithNoBills() {
        billIterator.loadBills(); // Ensure bills are loaded
        assertFalse(billIterator.hasNext());
    }

    @Test
    public void testNextWithNoBills() {
        billIterator.loadBills(); // Ensure bills are loaded
        assertNull(billIterator.next());
    }

    @Test
    public void testNextAfterExhaustion() {
        Bill bill = new Bill();
        bills.add(bill);
        billIterator.loadBills(); // Ensure bills are loaded

        billIterator.next(); // Move to the end
        assertNull(billIterator.next());
    }
}
