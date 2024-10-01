package core;

public class StockToShelfCheckInterface  implements PointOfSales{
    @Override
    public void getInterface() {
        StockItemRepository stockItemRepository = new StockItemRepository(database.Database.getInstance());
        StockToShelf stockToShelf = new StockToShelf(stockItemRepository);
        stockToShelf.moveItemsToShelfFromDatabase();

    }
}
