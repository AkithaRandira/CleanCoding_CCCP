package org.example;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws SQLException {

        try {

            Class.forName("com.mysql.cj.jdbc.Driver");


            Connection conn = DriverManager.getConnection("jdbc:mysql://localhost:3306/syos_system", "root", "");
            System.out.println("\n[INFO] Database connection established successfully.");

        } catch (ClassNotFoundException e) {
            System.err.println("[ERROR] MySQL JDhere is the BC Driver not found: " + e.getMessage());
        } catch (SQLException e) {
            System.err.println("[ERROR] Error establishing database connection: " + e.getMessage());
        }

        Scanner scanner = new Scanner(System.in);

        while (true) {
            System.out.println("\n=== MAIN MENU ===");
            System.out.println("1. Reports Menu");
            System.out.println("2. Point of Sales (POS) Menu");
            System.out.println("3. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {
                case 1:
                    showReportsMenu();
                    break;

                case 2:
                    showPointOfSalesMenu();
                    break;

                case 3:
                    System.exit(0);
                    break;

                default:
                    System.out.println("[ERROR] Invalid choice. Please try again.");
            }
        }
    }

    private static void showPointOfSalesMenu() {
        Scanner scanner1 = new Scanner(System.in);
        System.out.println("MENU " +
                "1. Add Bill " +
                "2. Customer Management " +
                "3. Product Management " +
                "4. Stock Management " +
                "5. Shelf Management " +
                "6. Stock to Shelf Check");

        int choice1 = scanner1.nextInt();
        PointOfSalesMenu pointOfSalesMenu = new PointOfSalesMenu();
        PointOfSales pointOfSales = pointOfSalesMenu.getInterface(choice1);
        pointOfSales.getInterface();
    }

    private static void showReportsMenu() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Print report:" +
                "1. Stock Report" +
                "2. Re-order Report" +
                "3. Bill Report" +
                "4. Sales Report" +
                "5. End of Day Report");
        int choice = scanner.nextInt();
        ReportFacade reportFacade = new ReportFacade();

        switch (choice) {
            case 1:
                reportFacade.generateStockReport();
                break;
            case 2:
                reportFacade.generateReOrderReport();
                break;
            case 3:
                reportFacade.generateBillReport();
                break;
            case 4:
                reportFacade.generateSalesReport();
                break;
            case 5:
                reportFacade.generateEndOfDayReport();
                break;
            default:
                System.out.println("Invalid choice");
        }
    }
}