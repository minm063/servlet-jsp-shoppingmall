package com.nhnacademy.shoppingmall.address.exception;

public class AddressNotFoundException extends RuntimeException{
    public AddressNotFoundException(String addressId){
        super(String.format("user not found:"+addressId));
    }
}
