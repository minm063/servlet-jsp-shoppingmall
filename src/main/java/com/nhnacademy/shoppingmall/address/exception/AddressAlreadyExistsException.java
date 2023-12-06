package com.nhnacademy.shoppingmall.address.exception;

public class AddressAlreadyExistsException extends RuntimeException {
    public AddressAlreadyExistsException(String addressId){
        super(String.format("user already exist:%s",addressId));
    }
}
