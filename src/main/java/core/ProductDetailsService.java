package core;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDetailsService implements ProductDetailsProvider {

    @Override
    public Product fetchProductDetails(int productId) {
        Product product = null;
        String query = "SELECT item_id, itemdescription, itemCode, unitprice FROM item WHERE item_id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String productName = rs.getString("itemdescription");
                    product = new Product(productId, productName);
                } else {
                    System.out.println("Product not found for Product ID: " + productId);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }

}
