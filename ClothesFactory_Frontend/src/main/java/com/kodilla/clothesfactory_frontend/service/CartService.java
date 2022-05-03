package com.kodilla.clothesfactory_frontend.service;

import com.kodilla.clothesfactory_frontend.configuration.client.CartClient;
import com.kodilla.clothesfactory_frontend.domain.Cart;

import java.util.ArrayList;
import java.util.List;

public class CartService {
    private List<Cart> carts;

    private static CartService cartService;

    private CartClient cartClient = CartClient.getInstance();

    private CartService(List<Cart> carts) {
        this.carts = carts;
    }

    public static CartService getInstance() {
        if(cartService == null) {
            cartService = new CartService(new ArrayList<Cart>());
        }
        return cartService;
    }

    public Cart getCartByUser(int userId) {
        return cartClient.getCart(userId);
    }

    public void addClothToCart(int cartId, int clothId) {
        cartClient.addClothToCart(cartId, clothId);
    }

    public void deleteClothFromCart(int cartId, int clothId) {
        cartClient.deleteClothFromCart(cartId, clothId);
    }
}
