package core;

import database.Database;
import junit.framework.TestCase;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;

import static org.mockito.Mockito.*;

public class ProductRepositoryTest extends TestCase {

    private ProductRepository productRepository;
    private Connection mockConnection;
    private PreparedStatement mockPreparedStatement;
    private ResultSet mockResultSet;

    protected void setUp() throws Exception {
        productRepository = new ProductRepository();
        mockConnection = mock(Connection.class);
        mockPreparedStatement = mock(PreparedStatement.class);
        mockResultSet = mock(ResultSet.class);

        when(Database.connect()).thenReturn(mockConnection);
    }

    public void testAddProductSuccess() throws Exception {
        String productName = "Product A";
        Integer productCategoryID = 1;

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        doNothing().when(mockPreparedStatement).executeUpdate();

        productRepository.addProduct(productName, productCategoryID);

        verify(mockPreparedStatement).setString(1, productName);
        verify(mockPreparedStatement).setInt(2, productCategoryID);
        verify(mockPreparedStatement).executeUpdate();
    }

    public void testAddProductSQLException() throws Exception {
        String productName = "Product A";
        Integer productCategoryID = 1;

        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Insert error"));

        try {
            productRepository.addProduct(productName, productCategoryID);
            fail("Expected SQLException");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Insert error"));
        }
    }

    public void testGetAllProductsSuccess() throws Exception {
        List<Product> expectedProducts = new ArrayList<>();
        expectedProducts.add(new Product(1, "Product A", 1));
        expectedProducts.add(new Product(2, "Product B", 2));

        when(mockConnection.prepareStatement(anyString())).thenReturn(mockPreparedStatement);
        when(mockPreparedStatement.executeQuery()).thenReturn(mockResultSet);

        when(mockResultSet.next()).thenReturn(true, true, false);
        when(mockResultSet.getInt("productid")).thenReturn(1, 2);
        when(mockResultSet.getString("productname")).thenReturn("Product A", "Product B");
        when(mockResultSet.getInt("productcategoryid")).thenReturn(1, 2);

        List<Product> products = productRepository.getAllProducts();

        assertEquals("Product list size should be 2", 2, products.size());
        assertEquals("First product name should be 'Product A'", "Product A", products.get(0).getProductName());
        assertEquals("Second product name should be 'Product B'", "Product B", products.get(1).getProductName());
    }

    public void testGetAllProductsSQLException() throws Exception {
        when(mockConnection.prepareStatement(anyString())).thenThrow(new SQLException("Select error"));

        try {
            productRepository.getAllProducts();
            fail("Expected SQLException");
        } catch (Exception e) {
            assertTrue(e.getMessage().contains("Select error"));
        }
    }
}
