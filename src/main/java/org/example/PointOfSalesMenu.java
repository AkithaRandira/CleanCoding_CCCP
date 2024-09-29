package org.example;

public class PointOfSalesMenu {

    public PointOfSales getInterface(int type){
        if(type==1){
            return new BillInterface();
        }
        else if(type==2){
            return new CustomerTaskInterface();
        }
        else if(type==3){
            return new ProductTaskInterface();
        } else if (type==4) {
            return  new StockInterface();

        } else if (type==5) {
            return  new ItemsOnShelfInterface();
        } else if (type==6) {
            return new StockToShelfCheckInterface();
        }

        else{
            return null;
        }

    }

}
