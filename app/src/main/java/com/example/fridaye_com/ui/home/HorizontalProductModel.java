package com.example.fridaye_com.ui.home;

public class HorizontalProductModel {
    private int productImage;
    private String product_title;
    private String product_description;
    private String product_price;

    public HorizontalProductModel(int productImage, String product_title, String product_description, String product_price) {
        this.productImage = productImage;
        this.product_title = product_title;
        this.product_description = product_description;
        this.product_price = product_price;
    }

    public int getProductImage() {
        return productImage;
    }

    public void setProductImage(int productImage) {
        this.productImage = productImage;
    }

    public String getProduct_title() {
        return product_title;
    }

    public void setProduct_title(String product_title) {
        this.product_title = product_title;
    }

    public String getProduct_description() {
        return product_description;
    }

    public void setProduct_description(String product_description) {
        this.product_description = product_description;
    }

    public String getProduct_price() {
        return product_price;
    }

    public void setProduct_price(String product_price) {
        this.product_price = product_price;
    }
}
