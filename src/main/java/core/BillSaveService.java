package core;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BillSaveService implements BillDatabaseServiceInterface {

    @Override
    public void saveBill(Bill bill) {

        String billSQL = "INSERT INTO bill (billSerialNumber, dateOfBill, subTotal, discount, netTotal, cashTendered, changeAmount, totalQuantitiesSold,paymentMethod,customerid) VALUES (?, ?, ?, ?, ?, ?, ?,?, ?,?)";
        String billItemSQL = "INSERT INTO billItem (billSerialNumber, itemCode, qtyperitem, priceperitem, totalamount) VALUES (?, ?, ?, ?, ?)";
        String updateItemQty = "UPDATE item SET qtyonshelf = qtyonshelf - ? WHERE itemcode = ?";

        try (Connection conn = Database.connect();
             PreparedStatement billStmt = conn.prepareStatement(billSQL);
             PreparedStatement billItemStmt = conn.prepareStatement(billItemSQL);
             PreparedStatement updateItemStmt = conn.prepareStatement(updateItemQty)) {

            conn.setAutoCommit(false);

            // Insert bill
            billStmt.setInt(1, bill.getBillSerialNumber());
            billStmt.setDate(2, new java.sql.Date(bill.getDateOfBill().getTime()));
            billStmt.setDouble(3, bill.getSubTotal());
            billStmt.setDouble(4, bill.getDiscount());
            billStmt.setDouble(5, bill.getNetTotal());
            billStmt.setDouble(6, bill.getCashTendered());
            billStmt.setDouble(7, bill.getChangeAmount());
            billStmt.setInt(8,bill.getTotalQuantitiesSold());
            billStmt.setString(9, bill.getPaymentMethod());
            int customerId = fetchCustomerIdByName(bill.getCustomerName());
            billStmt.setInt(10, customerId);
            billStmt.executeUpdate();

            // Insert bill items
            for (BillItem item : bill.getBillItems()) {
                billItemStmt.setInt(1, bill.getBillSerialNumber());
                billItemStmt.setInt(2, Integer.parseInt(item.getItemCode()));
                billItemStmt.setInt(3, item.getQuantity());
                billItemStmt.setDouble(4, item.getUnitPrice());
                billItemStmt.setDouble(5, item.getTotalPrice());
                billItemStmt.executeUpdate();

                // Update item quantity on shelf after each bill item is inserted
                updateItemStmt.setInt(1, item.getQuantity());
                updateItemStmt.setInt(2, Integer.parseInt(item.getItemCode()));
                updateItemStmt.executeUpdate();
            }

            conn.commit();
        } catch (SQLException e) {
            e.printStackTrace();
            try {
                Connection conn = Database.connect();
                conn.rollback();
            } catch (SQLException rollbackEx) {
                rollbackEx.printStackTrace();
            }
        }
    }

    private int fetchCustomerIdByName(String customerName) throws SQLException {
        String query = "SELECT customerid FROM customer WHERE customerName = ?";
        try (Connection conn = Database.connect();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, customerName);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getInt("customerid");
            } else {
                throw new SQLException("Customer not found");
            }
        }
    }

}
