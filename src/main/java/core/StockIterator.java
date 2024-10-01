package core;

import database.Database;

import java.sql.*;
import java.util.List;

public class StockIterator implements Iterator {

    private List<Stock> stocks;
    private int position = 0;

    public StockIterator(List<Stock> stocks) {
        this.stocks = stocks;

    }

    public void loadStock(){

        String SQL_SELECT = "SELECT stockitemid,itemname, itemcode, batchcode,quantityinstock,expirydate,manufacturedate,batchdate FROM stockitem";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int stockItemId = rs.getInt("stockitemid");
                String itemName = rs.getString("itemname");
                String itemCode = String.valueOf(rs.getString("itemcode"));
                String batchCode = String.valueOf(rs.getInt("batchcode"));
                int quantityInStock = rs.getInt("quantityinstock");
                Date expiryDate = rs.getDate("expirydate");
                Date manufactureDate = rs.getDate("manufacturedate");
                Date batchDate = rs.getDate("batchdate");
                stocks.add(new Stock(stockItemId,itemName, itemCode, batchCode, quantityInStock, expiryDate, manufactureDate, batchDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading stocks: " + e.getMessage());
        }

    }

    public void reOrderStockLevels(){

        String SQL_SELECT = "SELECT stockitemid,itemname, itemcode, batchcode, quantityinstock, expirydate, manufacturedate, batchdate " + "FROM stockitem " + "WHERE quantityinstock < 50";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int stockItemId = rs.getInt("stockitemid");
                String itemName = rs.getString("itemname");
                String itemCode = String.valueOf(rs.getString("itemcode")); // Convert to String
                String batchCode = String.valueOf(rs.getInt("batchcode"));
                int quantityInStock = rs.getInt("quantityinstock");
                Date expiryDate = rs.getDate("expirydate");
                Date manufactureDate = rs.getDate("manufacturedate");
                Date batchDate = rs.getDate("batchdate");
                stocks.add(new Stock(stockItemId,itemName, itemCode, batchCode, quantityInStock, expiryDate, manufactureDate, batchDate));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading stocks: " + e.getMessage());
        }

    }

    @Override
    public boolean hasNext() {
        return position<stocks.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return stocks.get(position++);
        }
        return null;
    }


}
