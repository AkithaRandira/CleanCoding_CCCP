package Reports;

import junit.framework.TestCase;
import static org.mockito.Mockito.*;

public class EndOfDayReportTest extends TestCase {

    private EndOfDayReport endOfDayReport;

    protected void setUp() {
        endOfDayReport = new EndOfDayReport();
    }

    public void testGetData() {
        EndOfDayReport spyEndOfDayReport = spy(endOfDayReport);

        spyEndOfDayReport.getData();

        verify(spyEndOfDayReport, times(1)).getData();
        System.out.println("Verified that getData() is called once.");
    }

    public void testCreateReport() {
        EndOfDayReport spyEndOfDayReport = spy(endOfDayReport);

        spyEndOfDayReport.createReport();

        verify(spyEndOfDayReport, times(1)).createReport();
        System.out.println("Verified that createReport() is called once.");
    }
}
