package com.dev.controllers;

import com.dev.objects.Offer;
import com.dev.objects.Product;
import com.dev.objects.User;
import com.dev.responses.*;
import com.dev.utils.Persist;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.persistence.Id;
import java.util.List;
import java.util.stream.Collectors;

import static com.dev.utils.Errors.*;

@RestController
public class DashboardController {

    @Autowired
    private Persist persist;

    @Autowired
    private LiveUpdatesController liveUpdatesController;

    @RequestMapping(value = "get-username", method = RequestMethod.GET)
    public BasicResponse getUsername(String token) {
        User user = persist.getUserByToken(token);
        BasicResponse basicResponse = null;
        if (user != null) {
            basicResponse = new UsernameResponse(true, null, user.getUsername());
        } else {
            basicResponse = new BasicResponse(false, ERROR_NO_SUCH_TOKEN);
        }
        return basicResponse;
    }


    @RequestMapping(value = "post-product", method = RequestMethod.POST)
    public BasicResponse postProduct(String token, String productName, String productDescription, int startingPrice, String productImg) {
        Integer errorCode = null;
        boolean success = false;
        BasicResponse basicResponse = new BasicResponse();
        User user = persist.getUserByToken(token);
        if (user != null) {
            Product product = new Product(productName, productImg, productDescription, startingPrice, user);
            if (product.getProductDescription() != null) {
                if (product.getProductImg() != null) {
                    if (product.getProductName() != null) {
                        if (product.getProductStartingPrice() != 0) {
                            success = true;
                            persist.saveProduct(product);
                            basicResponse = new ProductResponse(product);
                        } else {
                            errorCode = ERROR_NO_STARTING_PRICE_PRODUCT;
                        }
                    } else {
                        errorCode = ERROR_NO_PRODUCT_NAME;
                    }
                } else {
                    errorCode = ERROR_NO_PRODUCT_IMG;
                }
            } else {
                errorCode = ERROR_NO_PRODUCT_DESCRIPTION;
            }
        }
        basicResponse.setSuccess(success);
        basicResponse.setErrorCode(errorCode);
        return basicResponse;
    }

    //TODO:CLOOOOOSE PRODUCT!!!!

    @RequestMapping(value = "close-product", method = RequestMethod.POST)
    public BasicResponse closeProduct(String token, int productId) {
        Integer errorCode = null;
        boolean success = false;
        BasicResponse basicResponse;
        Offer highestOffer = persist.getHighestOffer(productId);
        Product product = persist.getProductById(productId);
        List<Offer> offersOnProduct = persist.getAmountOfOffersOnProduct(productId);
        int offerUser = highestOffer.getOfferFrom().getId();
        User user = persist.getUserByToken(token);
        if (offersOnProduct.size() >= 3) {
            if(token.equals(product.getPublisher().getToken())) {
                // CHECK IF HAVE MONEY
                persist.payCredit(offerUser,user.getId(),highestOffer.getOfferAmount());
                success = true;
                persist.closeProduct(productId);
            }
            } else {
            errorCode = ERROR_NO_3_OFFERS;
        }
        basicResponse = new ProductResponse(success, errorCode,product);
        return basicResponse;
    }

}
