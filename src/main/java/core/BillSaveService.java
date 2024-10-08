package core;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillSaveService implements BillDatabaseServiceInterface {

    @Override
    public void saveBill(Bill bill) {
        Connection conn = null;
        String billSQL = "INSERT INTO bill (billSerialNumber, dateOfBill, subTotal, discount, netTotal, cashTendered, changeAmount, totalQuantitiesSold, paymentMethod, customerid) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        String billItemSQL = "INSERT INTO billItem (billSerialNumber, itemCode, qtyperitem, priceperitem, totalamount) VALUES (?, ?, ?, ?, ?)";
        String updateItemQty = "UPDATE item SET qtyonshelf = qtyonshelf - ? WHERE itemcode = ?";

        try {
            conn = Database.connect();
            conn.setAutoCommit(false);

            int customerId = fetchCustomerIdByName(bill.getCustomerName(), conn);

            try (PreparedStatement billStmt = conn.prepareStatement(billSQL);
                 PreparedStatement billItemStmt = conn.prepareStatement(billItemSQL);
                 PreparedStatement updateItemStmt = conn.prepareStatement(updateItemQty)) {

                billStmt.setInt(1, bill.getBillSerialNumber());
                billStmt.setDate(2, new java.sql.Date(bill.getDateOfBill().getTime()));
                billStmt.setDouble(3, bill.getSubTotal());
                billStmt.setDouble(4, bill.getDiscount());
                billStmt.setDouble(5, bill.getNetTotal());
                billStmt.setDouble(6, bill.getCashTendered());
                billStmt.setDouble(7, bill.getChangeAmount());
                billStmt.setInt(8, bill.getTotalQuantitiesSold());
                billStmt.setString(9, bill.getPaymentMethod());
                billStmt.setInt(10, customerId);
                billStmt.executeUpdate();

                for (BillItem item : bill.getBillItems()) {
                    billItemStmt.setInt(1, bill.getBillSerialNumber());
                    billItemStmt.setString(2, item.getItemCode());
                    billItemStmt.setInt(3, item.getQuantity());
                    billItemStmt.setDouble(4, item.getUnitPrice());
                    billItemStmt.setDouble(5, item.getTotalPrice());
                    billItemStmt.executeUpdate();

                    updateItemStmt.setInt(1, item.getQuantity());
                    updateItemStmt.setString(2, item.getItemCode());
                    updateItemStmt.executeUpdate();
                }

                conn.commit();
            }
        } catch (SQLException e) {
            e.printStackTrace();
            handleRollback(conn);
        } finally {
            if (conn != null) {
                try {
                    conn.close();
                } catch (SQLException closeEx) {
                    closeEx.printStackTrace();
                }
            }
        }
    }

    private int fetchCustomerIdByName(String customerName, Connection conn) throws SQLException {
        String query = "SELECT customerid FROM customer WHERE customerName = ?";
        try (PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("customerid");
            } else {
                throw new SQLException("Customer not found");
            }
        }
    }

    private void handleRollback(Connection conn) {
        if (conn != null) {
            try {
                conn.rollback();
                System.err.println("Transaction rolled back successfully.");
            } catch (SQLException rollbackEx) {
                System.err.println("Error during rollback: " + rollbackEx.getMessage());
                rollbackEx.printStackTrace();
            }
        }
    }
}
