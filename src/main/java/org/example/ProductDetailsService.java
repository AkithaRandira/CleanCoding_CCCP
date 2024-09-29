package org.example;

import database.Database;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ProductDetailsService implements ProductDetailsProvider {

    @Override
    public Product fetchProductDetails(int productId) {
        Product product = null;
        String query = "SELECT item_id, item_name, item_code, item_price FROM ITEM WHERE item_id = ?";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setInt(1, productId);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    String productName = rs.getString("productname");
                    product = new Product(productId, productName);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return product;
    }
}
