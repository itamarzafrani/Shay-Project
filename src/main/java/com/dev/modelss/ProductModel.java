package com.dev.modelss;

import com.dev.objects.Product;
import com.dev.objects.User;

import java.text.SimpleDateFormat;

public class ProductModel {
    private int id;
    private String productName;
    private String productImg;
    private String productDescription;

    private String publisherName;
    private int productStartingPrice;

    public ProductModel() {
    }

    public ProductModel(Product product) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productImg = product.getProductImg();
        this.productDescription = product.getProductDescription();
        this.productStartingPrice = product.getProductStartingPrice();
        this.publisherName = product.getPublisher().getUsername();
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductImg() {
        return productImg;
    }

    public void setProductImg(String productImg) {
        this.productImg = productImg;
    }

    public String getProductDescription() {
        return productDescription;
    }

    public void setProductDescription(String productDescription) {
        this.productDescription = productDescription;
    }

    public int getProductStartingPrice() {
        return productStartingPrice;
    }

    public void setProductStartingPrice(int productStartingPrice) {
        this.productStartingPrice = productStartingPrice;
    }

    public String getPublisherName() {
        return publisherName;
    }

    public void setPublisherName(String publisherName) {
        this.publisherName = publisherName;
    }
}
