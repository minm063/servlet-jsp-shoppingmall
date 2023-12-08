package com.nhnacademy.shoppingmall.cart.exception;

public class CartAlreadyExistsException extends RuntimeException {
    public CartAlreadyExistsException(String cartId) {
        super(String.format("cart already exist:%s", cartId));
    }
}
