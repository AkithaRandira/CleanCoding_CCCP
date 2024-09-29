package org.example;

import Reports.*;

import java.util.ArrayList;
import java.util.List;

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
        Report report = new org.example.Reports.BillReport();
        report.reportGenerator();
    }
}
