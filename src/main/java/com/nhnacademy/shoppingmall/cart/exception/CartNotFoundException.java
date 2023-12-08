package com.nhnacademy.shoppingmall.cart.exception;

public class CartNotFoundException extends RuntimeException {
    public CartNotFoundException(String cartId) {
        super(String.format("cart not found:" + cartId));
    }
}
