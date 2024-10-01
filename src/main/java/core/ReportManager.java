package core;

import Reports.SalesReport;

public class ReportManager {
    public static void main(String[] args){

        Report report=new SalesReport();
        report.createReport();
    }
}
