package com.nhnacademy.shoppingmall.product.domain;

public class ProductCategory {
    private int productId;
    private int categoryId;

    public ProductCategory(int productId, int categoryId) {
        this.productId = productId;
        this.categoryId = categoryId;
    }

    public int getProductId() {
        return productId;
    }

    public int getCategoryId() {
        return categoryId;
    }
}
