package org.example;

public class ProductCategory {
    private int productCategoryId;
    private String productCategoryName;

    public ProductCategory(int productCategoryId, String productCategoryName) {
        this.productCategoryId = productCategoryId;
        this.productCategoryName = productCategoryName;
    }

    public int getProductCategoryId() {
        return productCategoryId;
    }

    public String getProductCategoryName() {
        return productCategoryName;
    }

    @Override
    public String toString() {
        return productCategoryName;
    }
}
