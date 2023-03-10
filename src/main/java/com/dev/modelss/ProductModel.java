package com.dev.modelss;

import com.dev.objects.Product;
import com.dev.objects.User;

import java.text.SimpleDateFormat;

public class ProductModel {
    private int id;
    private String productName;
    private String productImg;
    private String productDescription;

    private int publisherId;
    private int productStartingPrice;
    private boolean postedByMe = false;


    public ProductModel() {

    }

    public ProductModel(Product product ) {
        this.id = product.getId();
        this.productName = product.getProductName();
        this.productImg = product.getProductImg();
        this.productDescription = product.getProductDescription();
        this.productStartingPrice = product.getProductStartingPrice();
        this.publisherId = product.getPublisher().getId();
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

    public boolean isPostedByMe() {
        return postedByMe;
    }

    public void setPostedByMe(boolean postedByMe) {
        this.postedByMe = postedByMe;
    }
}
