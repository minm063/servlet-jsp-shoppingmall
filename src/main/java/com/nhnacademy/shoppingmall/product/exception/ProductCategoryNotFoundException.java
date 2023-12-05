package com.nhnacademy.shoppingmall.product.exception;

public class ProductCategoryNotFoundException extends RuntimeException{
    public ProductCategoryNotFoundException(String productCategoryName){
        super(String.format("productCategory not exist:%s",productCategoryName));
    }

}
