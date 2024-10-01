package Reports;

import core.Report;

public class EndOfDayReport extends Report {
    @Override
    public void getData() {

        System.out.println("Getting data for End of Day Report");
    }

    @Override
    public void createReport() {

        System.out.println("Creating End of Day Report");
    }
}
