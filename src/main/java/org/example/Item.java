package org.example;
import database.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class Item {
    List<Observer> observers=new ArrayList<Observer>();
    private static final int SHELF_SIZE = 20;
    private String itemCode;
    private String itemDescription;
    private double unitPrice;
    private Integer quantityOnShelf;
    private Product product;
    public Item() {}

    public Item(String itemCode, String itemDescription, double unitPrice) {
        this.itemCode=itemCode;
        this.itemDescription=itemDescription;
        this.unitPrice=unitPrice;
    }

    public Item(String itemCode, String itemDescription, double unitPrice, Product product) {
        this.itemCode=itemCode;
        this.itemDescription=itemDescription;
        this.unitPrice=unitPrice;
        this.product=product;

    }
    public String getItemCode() {
        return itemCode;
    }

    public void setItemCode(String itemCode) {
        this.itemCode = itemCode;
    }

    public String getItemDescription() {
        return itemDescription;
    }

    public void setItemDescription(String itemDescription) {
        this.itemDescription = itemDescription;
    }

    public double getUnitPrice() {
        return unitPrice;
    }

    public String getItemName(){
        return product.getProductName();
    }

    public void setUnitPrice(double unitPrice) {

        this.unitPrice = unitPrice;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }


    public Integer getQuantityOnShelf() {
        if (quantityOnShelf > SHELF_SIZE) {
            notifyAllObservers();
        }
        return quantityOnShelf;
    }

    public void setQuantityOnShelf(Integer quantityOnShelf) throws Exception {
        if (quantityOnShelf > SHELF_SIZE) {
            throw new Exception("Shelf is full");

        }
        this.quantityOnShelf = quantityOnShelf;
        notifyAllObservers();

    }
    public void attach(Observer observer){
        observers.add(observer);
    }

    public void notifyAllObservers(){
        for (Observer observer : observers) {
            observer.update();
            observer.lowStockAlert();
        }
    }


}

