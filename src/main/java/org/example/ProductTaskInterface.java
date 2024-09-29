package org.example;


import Controller.ProductController;

public class ProductTaskInterface implements PointOfSales {

    @Override
    public void getInterface() {

        ProductView view = new ProductView();
        Product model = new Product();
        ProductRepository productRepository=new ProductRepository();
        ProductController controller = new ProductController(view, model,productRepository);

        view.setVisible(true);
    }
}
