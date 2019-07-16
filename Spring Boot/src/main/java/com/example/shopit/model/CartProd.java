package com.example.shopit.model;

import com.example.shopit.model.Cart;

import java.util.List;

public class CartProd {

    private List<Cart> cartList;

    public CartProd() {
    }

    public List<Cart> getCartList() {
        return cartList;
    }

    public void setCartList(List<Cart> cartList) {
        this.cartList = cartList;
    }
}
