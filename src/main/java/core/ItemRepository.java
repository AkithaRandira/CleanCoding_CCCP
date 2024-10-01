package core;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemRepository {
    private static final int SHELF_SIZE =20 ;
    private Item item;


    public void reStockShelf() {
        String SQL_SELECT_ITEMS = "SELECT i.itemcode, i.qtyonshelf, s.quantityinstock " +
                "FROM item i " +
                "JOIN stockitem s ON i.itemcode = s.itemcode";

        String SQL_UPDATE_ITEM = "UPDATE item SET qtyonshelf = ? WHERE itemcode = ?";
        String SQL_UPDATE_STOCK = "UPDATE stockitem SET quantityinstock = ? WHERE itemcode = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmtSelect = conn.prepareStatement(SQL_SELECT_ITEMS);
             ResultSet rs = pstmtSelect.executeQuery()) {

            try (PreparedStatement pstmtUpdateItem = conn.prepareStatement(SQL_UPDATE_ITEM);
                 PreparedStatement pstmtUpdateStock = conn.prepareStatement(SQL_UPDATE_STOCK)) {

                while (rs.next()) {
                    int itemCode = rs.getInt("itemcode");
                    int qtyOnShelf = rs.getInt("qtyonshelf");
                    int quantityInStock = rs.getInt("quantityinstock");

                    // Calculate the quantity to add to the shelf
                    int quantityToAdd = Math.min(SHELF_SIZE - qtyOnShelf, quantityInStock);

                    if (quantityToAdd > 0) {
                        // Update the quantity on the shelf
                        pstmtUpdateItem.setInt(1, qtyOnShelf + quantityToAdd);
                        pstmtUpdateItem.setInt(2, itemCode);
                        pstmtUpdateItem.executeUpdate();

                        // Update the stock quantity in stock
                        pstmtUpdateStock.setInt(1, quantityInStock - quantityToAdd);
                        pstmtUpdateStock.setInt(2, itemCode);
                        pstmtUpdateStock.executeUpdate();

                        System.out.println("Updated item code: " + itemCode +
                                " | Shelf quantity: " + (qtyOnShelf + quantityToAdd) +
                                " | Stock quantity: " + (quantityInStock - quantityToAdd));
                    }
                }

                System.out.println("All items restocked successfully!");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error restocking items: " + e.getMessage());
        }
    }







    public void addItemsOnShelf(Integer itemCode, String itemDescription, double unitPrice, Integer quantityOnShelf, Product product) {

        String SQL_INSERT = "INSERT INTO item(itemcode, itemdescription, unitprice, qtyonshelf, productid) VALUES(?, ?, ?, ?, ?)";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {

            pstmt.setInt(1, itemCode);
            pstmt.setString(2, itemDescription);
            pstmt.setDouble(3, unitPrice);
            pstmt.setInt(4, quantityOnShelf);
            pstmt.setInt(5, product.getProductID());


            pstmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding ItemsOnShelf to database: " + e.getMessage());
        }
    }

}
