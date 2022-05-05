package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.CartClient;
import com.kodilla.clothesfactory_frontend.domain.Cart;

public class CartService {

    private static CartService cartService;
    private final CartClient cartClient = CartClient.getInstance();

    public static CartService getInstance() {
        if(cartService == null) {
            cartService = new CartService(/*new ArrayList<>()*/);
        }
        return cartService;
    }

    public Cart getCartFromUser(int userId) {
        return cartClient.getCartFromUser(userId);
    }

    public void addClothToCart(int cartId, int clothId) {
        cartClient.addClothToCart(cartId, clothId);
    }

    public void deleteClothFromCart(int cartId, int clothId) {
        cartClient.deleteClothFromCart(cartId, clothId);
    }
}
