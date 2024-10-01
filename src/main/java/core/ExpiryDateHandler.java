package core;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ExpiryDateHandler implements BatchHandler{

    private BatchHandler nextHandler;

    public void setNextHandler(BatchHandler nextHandler) {
        this.nextHandler = nextHandler;
    }

    @Override
    public void handleMovingItemsToTheShelf(List<Stock> stocks) {

        List<Stock> sortedItems=new ArrayList<>(stocks);

        sortedItems.sort(Comparator.comparing(Stock::getBatchDate));

        List<Stock> itemsWithCloserExpiryDate = new ArrayList<>();
        for(Stock item: sortedItems){
            if(item.getExpiryDate().before(item.getBatchDate())){
                itemsWithCloserExpiryDate.add(item);
            }
        }

        if (!itemsWithCloserExpiryDate.isEmpty()) {
            // Pass the filtered items to the next handler (ShelfHandler)
            if (nextHandler != null) {
                nextHandler.handleMovingItemsToTheShelf(itemsWithCloserExpiryDate);
            }
        }


    }
}
