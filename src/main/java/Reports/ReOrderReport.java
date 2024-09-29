package Reports;

import java.util.ArrayList;
import java.util.List;

import org.example.Report;
import org.example.Stock;
import org.example.StockIterator;

public class ReOrderReport extends Report {

    protected List<Stock> stocks=new ArrayList<>();
    protected StockIterator stockIterator;

    @Override
    public void getData() {

        System.out.println("Getting data for ReOrder Report");
        StockIterator stockIterator=new StockIterator(stocks);
        stockIterator.reOrderStockLevels();
    }

    @Override
    public void createReport() {
        System.out.println("Creating ReOrder Report");
        for (Stock stock : stocks) {
            System.out.println("Batch Code: " + stock.getBatchCode());
            System.out.println("Item Name: " + stock.getItemName());
            System.out.println("Item Code: " + stock.getItemCode());
            System.out.println("Quantity in Stock: " + stock.getQuantityInStock());
            System.out.println("---------------------------------------------");
        }

    }


}
