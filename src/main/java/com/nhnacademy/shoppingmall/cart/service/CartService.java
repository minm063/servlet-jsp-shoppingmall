package com.nhnacademy.shoppingmall.cart.service;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;

public interface CartService {

    void save(Cart cart);

    List<Cart> getCartByUserId(String userId);

    void updateQuantitiesOnProduct(List<Cart> cartList, List<Integer> quantities);

    void deleteCartByUserId(String userId);
}
