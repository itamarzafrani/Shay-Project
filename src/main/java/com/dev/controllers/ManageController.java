package com.dev.controllers;

import com.dev.modelss.ProductModel;
import com.dev.objects.Offer;
import com.dev.objects.Product;
import com.dev.objects.User;
import com.dev.responses.AllProductsResponse;
import com.dev.responses.AllUsersResponse;
import com.dev.responses.BasicResponse;
import com.dev.responses.ProfitResponse;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ManageController {

    @Autowired
    private Persist persist;


    @RequestMapping(value = "get-all-users" , method = RequestMethod.GET)
    public BasicResponse getAllUsers () {
        List<User> users = persist.getAllUsers();
        AllUsersResponse allUsersResponse = new AllUsersResponse(true,null,users);
        return allUsersResponse;
    }

    @RequestMapping(value = "get-all-open-products" , method = RequestMethod.GET)
    public BasicResponse getAllOpenProducts () {
        List<Product> products = persist.getAllOpenProducts();
        AllProductsResponse allOpenProductsResponse = new AllProductsResponse(true,null,products);
        return allOpenProductsResponse;
    }

    @RequestMapping(value = "get-all-profit" , method = RequestMethod.GET)
    public BasicResponse getAllProfit () {
        double profit = 0;
        List<Product> products = persist.getAllProducts();
        List<Offer> closedProducts = persist.getAllClosedProducts();
        List<Offer> offers = persist.getAllOffers();
        for (Offer offer:closedProducts) {
            profit += offer.getOfferAmount();
        }
        profit = profit * 0.05;
        profit += profit + (products.size() * 2) + (offers.size());
        ProfitResponse profitResponse = new ProfitResponse(true,null,profit);
        return profitResponse;
    }
}
