package core;

import Reports.*;

public class ReportFacade {

    public void generateSalesReport() {

        Report report = new SalesReport();
        report.reportGenerator();
    }

    public void generateEndOfDayReport() {
        Report report = new EndOfDayReport();
        report.reportGenerator();
    }

    public void generateReOrderReport() {
        Report report = new ReOrderReport();
        report.reportGenerator();
    }

    public void generateStockReport() {
        Report report = new StockReport();
        report.reportGenerator();
    }

    public void generateBillReport() {
        Report report = new Reports.BillReport();
        report.reportGenerator();
    }
}
