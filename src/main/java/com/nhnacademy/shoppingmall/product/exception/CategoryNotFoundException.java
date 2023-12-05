package com.nhnacademy.shoppingmall.product.exception;

public class CategoryNotFoundException extends RuntimeException{
    public CategoryNotFoundException(String categoryName){
        super(String.format("category not exist:%s",categoryName));
    }

}
