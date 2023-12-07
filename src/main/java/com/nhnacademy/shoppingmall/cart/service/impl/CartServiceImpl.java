package com.nhnacademy.shoppingmall.cart.service.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;

public class CartServiceImpl implements CartService {

    private final CartRepositoryImpl cartRepository;
    public CartServiceImpl(CartRepositoryImpl cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void save(Cart cart) {

    }
}
