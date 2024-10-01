
package core;

import database.Database;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

public class ProductIterator implements Iterator {

    private List<Product> products;
    private int position = 0;

    public ProductIterator() {
        products = new ArrayList<>();
        loadProducts();
    }

    private void loadProducts() {
        String SQL_SELECT = "SELECT productid, productname, productcategoryid FROM product";

        try (Connection conn = Database.connect();
             PreparedStatement pstmt = conn.prepareStatement(SQL_SELECT);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("productid");
                String name = rs.getString("productname");
                int categoryId = rs.getInt("productcategoryid");
                products.add(new Product(id, name, categoryId));
            }

        } catch (SQLException e) {
            e.printStackTrace();
            System.out.println("Error loading products: " + e.getMessage());
        }
    }

    @Override
    public boolean hasNext() {
        return position < products.size();
    }

    @Override
    public Object next() {
        if (this.hasNext()) {
            return products.get(position++);
        }
        return null;
    }
}
