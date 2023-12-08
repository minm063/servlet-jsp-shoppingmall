package com.nhnacademy.shoppingmall.order.domain;

import java.time.LocalDateTime;

public class Order {
    private int orderId;

    private LocalDateTime orderDate;

    private LocalDateTime shipDate;

    private String userId;

    private int addressId;

    public Order(LocalDateTime orderDate, LocalDateTime shipDate, String userId, int addressId) {
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.userId = userId;
        this.addressId = addressId;
    }

    public Order(int orderId, LocalDateTime orderDate, LocalDateTime shipDate, String userId, int addressId) {
        this.orderId = orderId;
        this.orderDate = orderDate;
        this.shipDate = shipDate;
        this.userId = userId;
        this.addressId = addressId;
    }

    public int getOrderId() {
        return orderId;
    }

    public LocalDateTime getOrderDate() {
        return orderDate;
    }

    public LocalDateTime getShipDate() {
        return shipDate;
    }

    public String getUserId() {
        return userId;
    }

    public int getAddressId() {
        return addressId;
    }
}
