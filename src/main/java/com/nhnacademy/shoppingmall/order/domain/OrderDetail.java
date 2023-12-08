package com.nhnacademy.shoppingmall.order.domain;

public class OrderDetail {

    private int orderId;

    private int productId;

    private int quantity;

    private int unitCost;

    public OrderDetail(int orderId, int productId, int quantity, int unitCost) {
        this.orderId = orderId;
        this.productId = productId;
        this.quantity = quantity;
        this.unitCost = unitCost;
    }

    public int getOrderId() {
        return orderId;
    }

    public int getProductId() {
        return productId;
    }

    public int getQuantity() {
        return quantity;
    }

    public int getUnitCost() {
        return unitCost;
    }
}
