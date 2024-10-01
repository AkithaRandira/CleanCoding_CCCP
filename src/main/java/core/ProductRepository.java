package core;

import database.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductRepository {

    public void addProduct(String productName, Integer productCategoryID) {
        String SQL_INSERT = "INSERT INTO product(productname, productcategoryid) VALUES(?, ?)";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_INSERT)) {
            pstmt.setString(1, productName);
            pstmt.setInt(2, productCategoryID);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error adding Product to database: " + e.getMessage());
        }
    }

    public List<Product> getAllProducts() {
        List<Product> products = new ArrayList<>();
        String SQL_SELECT = "SELECT productid, productname, productcategoryid FROM product";
        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int productId = rs.getInt("productid");
                String productName = rs.getString("productname");
                int productCategoryId = rs.getInt("productcategoryid");
                products.add(new Product(productId, productName, productCategoryId));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error fetching products from the database: " + e.getMessage());
        }
        return products;
    }
}
