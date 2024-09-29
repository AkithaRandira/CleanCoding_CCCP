package Controller;

import org.example.*;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ProductController {
    private ProductView theView;
    private Product theModel;
    private ProductRepository productRepository;

    public ProductController(ProductView theView, Product theModel,ProductRepository productRepository) {
        this.theView = theView;
        this.theModel = theModel;
        this.productRepository=productRepository;

        initializeProductCategories();
        this.theView.addProductListener(new ProductListener());
    }

    private void initializeProductCategories() {
        ProductCategoryIterator iterator = new ProductCategoryIterator();
        List<ProductCategory> categories = new ArrayList<>();
        while (iterator.hasNext()) {
            categories.add((ProductCategory) iterator.next());
        }
        theView.setProductCategories(categories);
    }

    class ProductListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String productName;
            int productCategoryID;

            try {

                productName = theView.getProductName();
                if(productName==null||productName.trim().isEmpty()){
                    theView.displayError("Product name cannot be empty!");
                    return;
                }

                ProductCategory selectedCategory = theView.getSelectedProductCategory();
                productCategoryID = selectedCategory.getProductCategoryId();

                productRepository.addProduct(productName, productCategoryID);
                theView.setProduct(productName, selectedCategory.getProductCategoryName());
                theView.displaySuccessMessage("Product added successfully!");

            } catch (Exception exception) {
                theView.displayError("Error: " + exception.getMessage());
            }
        }
    }
}

