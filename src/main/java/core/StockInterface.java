package core;

import Controller.StockItemController;
import View.StockItemView;
import database.Database;

public class StockInterface implements PointOfSales {
    public void getInterface() {
        StockItemView view = new StockItemView();
        Stock model = new Stock();

        // Assuming you already have a method to get an instance of your database
        StockItemRepository stockItemRepository = new StockItemRepository(Database.getInstance());
        ProductRepository productRepository = new ProductRepository();

        // Ensure the correct number and type of arguments are passed
        new StockItemController(view, model, stockItemRepository, productRepository);

        view.setVisible(true);
    }
}
