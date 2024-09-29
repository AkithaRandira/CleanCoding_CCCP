package Controller;

import org.example.StockItemRepository;
import org.example.Stock;
import org.example.Product;
import org.example.ProductRepository; // Import ProductRepository
import View.StockItemView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Date;
import java.util.List;

public class StockItemController {

    private StockItemView theView;
    private Stock theModel;
    private StockItemRepository stockItemRepository;
    private ProductRepository productRepository; // ProductRepository to fetch products

    public StockItemController(StockItemView theView, Stock theModel, StockItemRepository stockItemRepository, ProductRepository productRepository) {
        this.theView = theView;
        this.theModel = theModel;
        this.stockItemRepository = stockItemRepository;
        this.productRepository = productRepository;

        // Populate the item name dropdown
        initializeProductDropdown();

        // Add action listener for the "Add" button
        this.theView.addBatchItemListener(new AddBatchItemListener());
    }

    // Populate item name dropdown with existing products
    private void initializeProductDropdown() {
        List<Product> products = productRepository.getAllProducts();
        theView.setProductDropdown(products); // Assuming setProductDropdown method is in StockItemView
    }

    class AddBatchItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String itemCode = theView.getItemCode();
                String batchCode = String.valueOf(theView.getBatchCode());
                Product selectedProduct = theView.getSelectedProduct(); // Retrieve selected product from dropdown
                Integer quantityInStock = Integer.parseInt(theView.getQuantityInStock().getText());
                Date expiryDate = parseDate(theView.getExpiryDate());
                Date manufactureDate = parseDate(theView.getManufactureDate());
                Date batchDate = parseDate(theView.getBatchDate());

                if (selectedProduct == null) {
                    theView.displayError("Please select a product.");
                    return;
                }

                stockItemRepository.addBatchItems(batchCode, itemCode, selectedProduct.getProductName(), quantityInStock, manufactureDate, expiryDate, batchDate);
                theView.displaySuccessMessage("Batch item added successfully for product: " + selectedProduct.getProductName());
            } catch (NumberFormatException ex) {
                theView.displayError("Invalid input. Please enter valid numbers.");
            }
        }

        private Date parseDate(String dateStr) {
            try {
                return Date.valueOf(dateStr);
            } catch (IllegalArgumentException ex) {
                theView.displayError("Invalid date format. Please enter date in yyyy-MM-dd format.");
                throw ex;
            }
        }

    }
}
