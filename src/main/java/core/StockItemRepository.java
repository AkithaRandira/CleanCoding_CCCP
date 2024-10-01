package core;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockItemRepository {

    private final Database database;
    public StockItemRepository(Database database){
        this.database=database;
    }
    public void addBatchItems(String batchCode, String itemCode, String itemName, Integer quantityInStock, Date manufactureDate, Date expiryDate, Date batchDate)
    {

        String SQL_INSERT = "INSERT INTO stockitem (batchCode, itemCode,itemname, quantityInStock, manufacturedate, expirydate, batchdate) VALUES (?,  ?, ?, ?, ?,?,?)";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setString(1, batchCode);
            pstmt.setString(2, itemCode);
            pstmt.setString(3, itemName);
            pstmt.setInt(4, quantityInStock);
            pstmt.setDate(5, manufactureDate);
            pstmt.setDate(6, expiryDate);
            pstmt.setDate(7, batchDate);

            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error adding Batch Item to database: " + e.getMessage());
        }
    }
    public List<Stock> retrieveBatchItems() {
        List<Stock> stocks = new ArrayList<>();
        String SQL_SELECT = "SELECT * FROM stockitem";

        try (Connection conn = database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT)) {
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                int stockItemId = rs.getInt("stockItemId");
                String batchCode = rs.getString("batchCode");
                String itemName = rs.getString("itemName");
                String itemCode = rs.getString("itemCode");
                int quantityInStock = rs.getInt("quantityInStock");
                Date manufactureDate = rs.getDate("manufactureDate");
                Date expiryDate = rs.getDate("expiryDate");
                Date batchDate = rs.getDate("batchDate");

                stocks.add(new Stock(stockItemId, batchCode, itemName, itemCode, quantityInStock, expiryDate, manufactureDate, batchDate));
            }
        } catch (SQLException e) {
            System.out.println("Error retrieving batch items from database: " + e.getMessage());
        }
        return stocks;
    }




}
