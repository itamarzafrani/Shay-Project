package com.dev.responses;

import com.dev.objects.Product;

public class ProductResponse extends BasicResponse{
    private Product product;


    public ProductResponse(Product product) {
        this.product = product;
    }

    public ProductResponse(boolean success, Integer errorCode, Product product) {
        super(success, errorCode);
        this.product = product;
    }



}
