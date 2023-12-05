package com.nhnacademy.shoppingmall.product.domain;

import java.math.BigDecimal;

public class Product {
    private int productId;
    private String productNumber;
    private String productName;
    private BigDecimal unitCost;
    private String description;
    private String productImage;
    private String thumbnail;

    public Product(String productNumber, String productName, BigDecimal unitCost, String description,
                   String productImage,
                   String thumbnail) {
        this.productNumber = productNumber;
        this.productName = productName;
        this.unitCost = unitCost;
        this.description = description;
        this.productImage = productImage;
        this.thumbnail = thumbnail;
    }

    public Product(int productId, String productNumber, String productName, BigDecimal unitCost, String description,
                   String productImage, String thumbnail) {
        this.productId = productId;
        this.productNumber = productNumber;
        this.productName = productName;
        this.unitCost = unitCost;
        this.description = description;
        this.productImage = productImage;
        this.thumbnail = thumbnail;
    }

    public int getProductId() {
        return productId;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public BigDecimal getUnitCost() {
        return unitCost;
    }

    public String getDescription() {
        return description;
    }

    public String getProductImage() {
        return productImage;
    }

    public String getThumbnail() {
        return thumbnail;
    }
}
