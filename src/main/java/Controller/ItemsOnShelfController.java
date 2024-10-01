package Controller;

import core.Item;
import core.ItemRepository;
import core.Product;
import core.ProductIterator;
import View.ItemsOnShelfView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

public class ItemsOnShelfController {

    private ItemsOnShelfView theView;
    private Item theModel;
    private ItemRepository itemRepository;

    public ItemsOnShelfController(ItemsOnShelfView theView, Item theModel,ItemRepository itemRepository) {
        this.theView = theView;
        this.theModel = theModel;
        this.itemRepository=itemRepository;

        initializeProducts();
        theView.addItemsOnShelfListener(new AddItemListener());
        theView.addRestockShelfListener(new RestockShelfListener());
    }

    private void initializeProducts() {
        ProductIterator iterator = new ProductIterator();
        List<Product> products = new ArrayList<>();
        while (iterator.hasNext()) {
            products.add((Product) iterator.next());
        }
        theView.setProducts(products);

    }

    class RestockShelfListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {

                itemRepository.reStockShelf();
                theView.displaySuccessMessage("Shelf restocked successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                theView.displayErrorMessage("Error restocking shelf: " + ex.getMessage());
            }
        }
    }


    class AddItemListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            java.sql.Date expiryDate;
            java.sql.Date manufactureDate;

            try {
                int itemCode = Integer.parseInt(theView.getItemCode());
                String itemDescription = theView.getItemDescription();
                double unitPrice = Double.parseDouble(theView.getUnitPrice());
                int quantityOnShelf = Integer.parseInt(theView.getQuantityOnShelf());
                Product product = new Product(Integer.parseInt(theView.getProductID()), "");


                itemRepository.addItemsOnShelf(itemCode, itemDescription, unitPrice, quantityOnShelf,product);
                theView.displaySuccessMessage("Item added successfully!");
            } catch (Exception ex) {
                ex.printStackTrace();
                theView.displayErrorMessage("Error adding item: " + ex.getMessage());
            }
        }
    }



}
