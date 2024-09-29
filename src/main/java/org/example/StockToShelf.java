package org.example;

import database.Database;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class StockToShelf {
    protected BatchHandler batchHandler;

    private final StockItemRepository stockItemRepository;

    public StockToShelf(StockItemRepository stockItemRepository) {

        this.stockItemRepository = stockItemRepository;

        ExpiryDateHandler expiryDateHandler = new ExpiryDateHandler();
        ShelfHandler shelfHandler = new ShelfHandler();

        expiryDateHandler.setNextHandler(shelfHandler);
        batchHandler = expiryDateHandler;
    }

    public void moveItemsToShelfFromDatabase() {
        try (Connection conn = Database.getInstance().connect()) {

            List<Stock> Stocks = stockItemRepository.retrieveBatchItems();
            batchHandler.handleMovingItemsToTheShelf(Stocks);
        } catch (SQLException e) {
            System.out.println("Error moving items to shelf: " + e.getMessage());
        }
    }



}
