package com.nhnacademy.shoppingmall.cart.service.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.exception.CartAlreadyExistsException;
import com.nhnacademy.shoppingmall.cart.exception.CartNotFoundException;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CartServiceImpl implements CartService {

    private final CartRepositoryImpl cartRepository;
    public CartServiceImpl(CartRepositoryImpl cartRepository) {
        this.cartRepository = cartRepository;
    }

    @Override
    public void save(Cart cart) {
        cartRepository.save(cart);
    }

    @Override
    public List<Cart> getCartByUserId(String userId) {
        if (cartRepository.countByUserId(userId) == 0) {
            return new ArrayList<>();
        }

        return cartRepository.findCartByUserId(userId);
    }

    @Override
    public void updateQuantitiesOnProduct(List<Cart> cartList, List<Integer> quantities) {
        for (int i = 0; i < cartList.size(); i++) {
            cartRepository.updateQuantity(cartList.get(i).getRecordId(), quantities.get(i));
            log.info("update ... ing : {}, {}", cartList.get(i).getRecordId(), quantities.get(i));
        }
    }

    @Override
    public void deleteCartByUserId(String userId) {
        if (cartRepository.countByUserId(userId) == 0) {
            throw new CartNotFoundException(userId);
        }

        cartRepository.deleteCartByUserId(userId);
    }
}
