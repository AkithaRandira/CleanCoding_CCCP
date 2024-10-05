package Controller;

import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import core.Product;
import core.ProductCategory;
import core.ProductRepository;
import core.ProductView;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import database.Database;

public class ProductControllerTest {

    private ProductView productView;
    private ProductRepository productRepository;
    private ProductController productController;
    private Database database;
    private Product product;

    @Before
    public void setUp() throws SQLException {
        productView = mock(ProductView.class);
        database = new Database(); // Use actual database
        productRepository = new ProductRepository();
        product = new Product();
        productController = new ProductController(productView, product, productRepository);

        // Clean up any test data before each test
        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE productname LIKE 'Test Product%'")) {
            statement.executeUpdate();
        }
    }

    @After
    public void tearDown() throws SQLException {
        // Clean up test data after each test
        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement("DELETE FROM product WHERE productname LIKE 'Test Product%'")) {
            statement.executeUpdate();
        }
    }

    @Test
    public void testProductCreationAndAdd() throws SQLException {
        // Prepare mock data
        ProductCategory testCategory = new ProductCategory(1, "Test Category");
        when(productView.getProductName()).thenReturn("Test Product");
        when(productView.getSelectedProductCategory()).thenReturn(testCategory);

        // Simulate the button click which triggers the actionPerformed method
        productController.new ProductListener().actionPerformed(null);

        // Verify view interactions
        verify(productView, times(1)).setProduct("Test Product", "Test Category");
        verify(productView, times(1)).displaySuccessMessage("Product added successfully!");

        // Verify the product was added to the database
        try (Connection connection = database.connect();
             PreparedStatement statement = connection.prepareStatement("SELECT * FROM product WHERE productname = 'Test Product' AND productcategoryid = ?")) {
            statement.setInt(1, testCategory.getProductCategoryId());
            ResultSet resultSet = statement.executeQuery();
            assertTrue("Product should be added to the database", resultSet.next());
        }
    }

    @Test
    public void testInvalidProductName() {
        // Prepare mock data with empty product name
        ProductCategory testCategory = new ProductCategory(1, "Test Category");
        when(productView.getProductName()).thenReturn("");
        when(productView.getSelectedProductCategory()).thenReturn(testCategory);

        // Simulate the button click which triggers the actionPerformed method
        productController.new ProductListener().actionPerformed(null);

        // Verify that an error message is displayed
        verify(productView, times(1)).displayError("Product name cannot be empty!");
    }
}
