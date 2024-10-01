package core;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class BillRepository {
    private List<Bill> bills = new ArrayList<>();

    public List<Bill> loadAllBills() {
        String sql = "SELECT * FROM bill";
        String sqlItems = "SELECT * FROM billitem WHERE billserialnumber = ?";

        try (Connection conn = Database.connect();
             Statement statement = conn.createStatement();
             ResultSet rs = statement.executeQuery(sql)) {

            while (rs.next()) {
                int billSerialNumber = rs.getInt("billserialnumber");
                double netTotal = rs.getDouble("nettotal");
                double discount = rs.getDouble("discount");
                double cashTendered = rs.getDouble("cashtendered");
                double changeAmount = rs.getDouble("changeamount");
                double subTotal = rs.getDouble("subtotal");
                Date dateOfBill = rs.getDate("dateofbill");
                int totalQuantitiesSold = rs.getInt("totalquantitiessold");
                String paymentStrategy = rs.getString("paymentmethod");
                String customerName = rs.getString("customername");

                List<BillItem> billItems = new ArrayList<>();
                try (PreparedStatement ps = conn.prepareStatement(sqlItems)) {
                    ps.setInt(1, billSerialNumber);
                    try (ResultSet rsItems = ps.executeQuery()) {
                        while (rsItems.next()) {
                            int itemCode = rsItems.getInt("itemcode");

                            int quantity = rsItems.getInt("qtyperitem");
                            double unitPrice = rsItems.getDouble("priceperitem");
                            double totalPrice = rsItems.getDouble("totalamount");

                            BillItem item = new BillItem(String.valueOf(itemCode), quantity, unitPrice, totalPrice);
                            billItems.add(item);
                        }
                    }
                }

                Bill bill = new Bill(billSerialNumber, netTotal, billItems, discount, cashTendered, changeAmount, subTotal, dateOfBill, totalQuantitiesSold,paymentStrategy,customerName);
                bills.add(bill);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return bills;
    }

}
