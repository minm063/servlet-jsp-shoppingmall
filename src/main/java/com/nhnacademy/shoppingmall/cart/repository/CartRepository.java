package com.nhnacademy.shoppingmall.cart.repository;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import java.util.List;

public interface CartRepository {
    int save(Cart cart);

    List<Cart> findCartByUserId(String userId);

    int countByUserId(String userId);

    int updateQuantity(int recordId, int quantity);

    int deleteCartByUserId(String userId);


}
