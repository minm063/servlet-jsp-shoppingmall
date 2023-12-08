package com.nhnacademy.shoppingmall.cart.domain;

import java.time.LocalDateTime;

public class Cart {

    private int recordId;

    private int quantity;

    private LocalDateTime dateCreated;

    private int productId;

    private String userId;

    public Cart(int quantity, LocalDateTime dateCreated, int productId, String userId) {
        this.quantity = quantity;
        this.dateCreated = dateCreated;
        this.productId = productId;
        this.userId = userId;
    }

    public Cart(int recordId, int quantity, LocalDateTime dateCreated, int productId, String userId) {
        this.recordId = recordId;
        this.quantity = quantity;
        this.dateCreated = dateCreated;
        this.productId = productId;
        this.userId = userId;
    }

    public int getRecordId() {
        return recordId;
    }

    public int getQuantity() {
        return quantity;
    }

    public LocalDateTime getDateCreated() {
        return dateCreated;
    }

    public int getProductId() {
        return productId;
    }

    public String getUserId() {
        return userId;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }
}
