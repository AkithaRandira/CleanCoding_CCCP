package core;

public class Product{
    private int productID;
    private String productName;
    private int productCategoryID;

    public Product(int productID, String productName, int productCategoryID) {
        this.productID = productID;
        this.productName = productName;
        this.productCategoryID = productCategoryID;
    }

    public Product() {

    }

    public Product(int productID, String productName) {
        this.productID = productID;
        this.productName = productName;
    }

    public Product(int productID) {
        this.productID = productID;

    }

    public String getProductName() {

        return productName;
    }

    public int getProductID() {

        return productID;
    }

    public  int getProductCategoryID(){
        return productCategoryID;
    }

    @Override
    public String toString() {
        return this.productName;
    }


}
