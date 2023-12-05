package com.nhnacademy.shoppingmall.product.exception;

public class ProductCategoryAlreadyExistsException extends RuntimeException{
    public ProductCategoryAlreadyExistsException(String productCategoryName){
        super(String.format("productCategory already exist:%s",productCategoryName));
    }

}
