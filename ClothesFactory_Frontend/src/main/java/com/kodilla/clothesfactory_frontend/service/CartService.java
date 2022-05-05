package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.CartsClient;
import com.kodilla.clothesfactory_frontend.domain.Cart;

public class CartService {

    private static CartService cartService;
    private final CartsClient cartsClient = CartsClient.getInstance();

    public static CartService getInstance() {
        if(cartService == null) {
            cartService = new CartService();
        }
        return cartService;
    }

    public Cart getCartFromUser(int userId) {
        return cartsClient.getCartFromUser(userId);
    }

    public void addClothToCart(int cartId, int clothId) {
        cartsClient.addClothToCart(cartId, clothId);
    }

    public void deleteClothFromCart(int cartId, int clothId) {
        cartsClient.deleteClothFromCart(cartId, clothId);
    }
}
