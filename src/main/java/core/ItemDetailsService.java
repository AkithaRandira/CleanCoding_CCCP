package core;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ItemDetailsService {
    ProductDetailsService productDetailsService;

    public ItemDetailsService(ProductDetailsService productDetailsService) {
        this.productDetailsService = productDetailsService;


    }

    public Item fetchItemDetails(String itemCode) {
        Item item = null;
        String query = "SELECT itemcode, itemdescription, unitprice, productid FROM item WHERE itemcode = ?";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {

            pstmt.setString(1, itemCode);

            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    Integer productId = rs.getInt("productid");
                    String itemDescription = rs.getString("itemdescription");
                    double unitPrice = rs.getDouble("unitprice");
                    Product product = productDetailsService.fetchProductDetails(productId);

                    item = new Item(itemCode, itemDescription, unitPrice, product);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return item;
    }

}
