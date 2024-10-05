package Reports;

import core.*;
import junit.framework.TestCase;

public class ProductDetailsServiceTest extends TestCase {

    public void setUp() throws Exception {
        super.setUp();

        ProductDetailsProvider productDetailsProvider;
        ProductRepository productRepository;
    }

    public void tearDown() throws Exception {

    }

    public void testFetchProductDetails() {
        ProductDetailsService productDetailsService = new ProductDetailsService();
        Product product = productDetailsService.fetchProductDetails(1);
        assertEquals("Carrot", product.getProductName());



    }
}
