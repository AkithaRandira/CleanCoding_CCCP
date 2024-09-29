package org.example;

import Reports.SalesReport;

import java.util.ArrayList;
import java.util.List;

public class ReportManager {
    public static void main(String[] args){

        Report report=new SalesReport();
        report.createReport();
    }
}
