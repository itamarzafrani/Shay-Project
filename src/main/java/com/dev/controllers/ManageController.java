package com.dev.controllers;

import com.dev.modelss.ProductModel;
import com.dev.objects.Offer;
import com.dev.objects.Product;
import com.dev.objects.User;
import com.dev.responses.*;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

import static com.dev.utils.Errors.ERROR_LOWER_OFFER_AMOUNT;

@RestController
public class ManageController {

    @Autowired
    private Persist persist;


    @RequestMapping(value = "get-all-users", method = RequestMethod.GET)
    public BasicResponse getAllUsers() {
        BasicResponse basicResponse = null;
        List<User> users = persist.getAllUsers();
        basicResponse = new AllUsersResponse(true, null, users);
        return basicResponse;
    }

    @RequestMapping(value = "get-all-open-products", method = RequestMethod.GET)
    public BasicResponse getAllOpenProducts() {
        List<Product> products = persist.getAllOpenProducts();
        AllProductsResponse allOpenProductsResponse = new AllProductsResponse(true, null, products);
        return allOpenProductsResponse;
    }


    @RequestMapping(value = "get-all-products", method = RequestMethod.GET)
    public BasicResponse getAllProducts() {
        BasicResponse basicResponse;
        List<Product> products = persist.getAllProducts();
        basicResponse = new AllProductsResponse(true, null, products);
        return basicResponse;
    }


    @RequestMapping(value = "get-all-profit", method = RequestMethod.GET)
    public BasicResponse getAllProfit() {
        double profit = 0;
        List<Product> products = persist.getAllProducts();
        List<Offer> closedProducts = persist.getAllClosedProducts();
        List<Offer> offers = persist.getAllOffers();
        for (Offer offer : closedProducts) {
            profit += offer.getOfferAmount();
        }
        profit = profit * 0.05;
        profit += profit + (products.size() * 2) + (offers.size());
        ProfitResponse profitResponse = new ProfitResponse(true, null, profit);
        return profitResponse;
    }


    //TODO: CONNECTION TO OFFER SYSTEM

    @RequestMapping(value = "post-offer", method = RequestMethod.POST)
    public BasicResponse postOffer(String token, int productId, double offerPrice) {
        BasicResponse basicResponse;
        boolean success = false;
        Integer errorCode = 0;
        Offer newOffer =null;
        User user = persist.getUserByToken(token);
        Product product = persist.getProductById(productId);
        Offer highestOffer = persist.getHighestOffer(productId);
        if (highestOffer == null){
             newOffer = new Offer(offerPrice, product, user);
            success = true;
            basicResponse = new OfferResponse(success, errorCode, newOffer);
            persist.saveOffer(newOffer);
        }else if(highestOffer.getOfferAmount() < offerPrice ) {
             newOffer = new Offer(offerPrice, product, user);
            success = true;
            basicResponse = new OfferResponse(success, errorCode, newOffer);
            persist.saveOffer(newOffer);
        } else {
            errorCode = ERROR_LOWER_OFFER_AMOUNT;
        }
        basicResponse = new OfferResponse(success, errorCode, newOffer);
        return basicResponse;
    }

}
