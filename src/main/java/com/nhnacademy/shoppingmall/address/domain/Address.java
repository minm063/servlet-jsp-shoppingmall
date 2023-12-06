package com.nhnacademy.shoppingmall.address.domain;

public class Address {
    private int addressId;
    private String address;
    private String userId;

    public Address(int addressId, String address, String userId) {
        this.addressId = addressId;
        this.address = address;
        this.userId = userId;
    }

    public Address(String address, String userId) {
        this.address = address;
        this.userId = userId;
    }

    public int getAddressId() {
        return addressId;
    }

    public String getAddress() {
        return address;
    }

    public String getUserId() {
        return userId;
    }
}
