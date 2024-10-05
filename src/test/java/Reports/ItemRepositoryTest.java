package Reports;

import core.*;

import database.Database;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.sql.*;


import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class ItemRepositoryTest {

    private static final int SHELF_SIZE =20 ;
    private Connection connection;
    private ItemRepository itemRepository;

    @Before
    public void setUp() {
        try {
            Class.forName("org.postgresql.Driver");
            connection = DriverManager.getConnection("jdbc:postgresql://localhost:5432/postgres", "postgres", "Sana@2002");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            fail("Failed to establish a database connection");
        }

        itemRepository = new ItemRepository();
    }


    @After
    public void tearDown() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    @Test
    public void testRestockShelf() {

        itemRepository.reStockShelf();

        // Verify if the items were restocked correctly by checking the database
        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM item");
            ResultSet resultSet = statement.executeQuery();
            while (resultSet.next()) {
                int qtyOnShelf = resultSet.getInt("qtyonshelf");
                // Verify if the qtyonshelf is updated correctly
                assertEquals(SHELF_SIZE, qtyOnShelf);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException occurred");
        }
    }


    @Test
    public void testAddItemsOnShelf() {

        Integer itemCode = 1001;
        String itemDescription = "Test Item";
        double unitPrice = 10.99;
        Integer quantityOnShelf = 20;
        Product product = new Product(1, "Test Product");


        itemRepository.addItemsOnShelf(itemCode, itemDescription, unitPrice, quantityOnShelf, product);


        try {
            PreparedStatement statement = connection.prepareStatement("SELECT * FROM item WHERE itemcode = ?");
            statement.setInt(1, itemCode);
            ResultSet resultSet = statement.executeQuery();
            if (resultSet.next()) {
                assertEquals(itemCode.intValue(), resultSet.getInt("itemcode"));
                assertEquals(itemDescription, resultSet.getString("itemdescription"));
                assertEquals(unitPrice, resultSet.getDouble("unitprice"), 0.01); // delta for double comparison
                assertEquals(quantityOnShelf.intValue(), resultSet.getInt("qtyonshelf"));
                assertEquals(product.getProductID(), resultSet.getInt("productid"));
            } else {
                fail("Item was not added to the database");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            fail("SQLException occurred");
        }
    }
}
