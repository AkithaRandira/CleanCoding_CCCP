package org.example;

public class StockObserver extends Observer{
    private Stock Stock;

    public StockObserver(Stock Stock){
        this.Stock = Stock;
        this.Stock.attach(this);
    }
    @Override
    public void update() {
        System.out.println("Observer is updated "+ Stock.getItemCode()+ "is updated with "+ Stock.getQuantityInStock());
    }

    @Override
    public void lowStockAlert() {
        System.out.println("Low Stock ! Re-order "+ Stock.getItemCode()+ " Quantity is at "+  Stock.getQuantityInStock() );
    }


}
