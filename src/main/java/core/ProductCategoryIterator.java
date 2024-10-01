package core;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryIterator implements Iterator {

    private List<ProductCategory> productCategories;
    private int position = 0;

    public ProductCategoryIterator() {
        productCategories = new ArrayList<>();
        loadProductCategories();
    }

    private void loadProductCategories() {
        String SQL_SELECT = "SELECT productcategoryid, productcategoryname FROM productcategory";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("productcategoryid");
                String name = rs.getString("productcategoryname");
                productCategories.add(new ProductCategory(id, name));
            }

        } catch (SQLException e) {
            e.printStackTrace();  // Print full stack trace for debugging
            System.out.println("Error loading product categories: " + e.getMessage());
        }
    }

    @Override
    public boolean hasNext() {
        return position < productCategories.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return productCategories.get(position++);
        }
        return null;
    }
}

