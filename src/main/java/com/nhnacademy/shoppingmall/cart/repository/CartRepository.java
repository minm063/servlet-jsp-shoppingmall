package com.nhnacademy.shoppingmall.cart.repository;

import com.nhnacademy.shoppingmall.cart.domain.Cart;

public interface CartRepository {

    int save(Cart cart);
}
