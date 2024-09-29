package org.example;

import java.util.List;

public class ShelfHandler implements BatchHandler{
    @Override
    public void handleMovingItemsToTheShelf(List<Stock> Stocks) {
        System.out.println("Item moving to the shelf:");
        for (Stock item : Stocks) {
            System.out.println("Item Code: " + item.getItemCode() + ", Expiry Date: " + item.getExpiryDate()+" Batch Date : "+item.getBatchDate());
        }
    }
}
