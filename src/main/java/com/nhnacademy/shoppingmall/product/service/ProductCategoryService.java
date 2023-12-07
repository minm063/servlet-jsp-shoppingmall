package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import java.util.List;

public interface ProductCategoryService {

    void saveProductCategory(ProductCategory productCategory);

    void updateProductCategory(ProductCategory productCategory);

    List<ProductCategory> getProductCategoryByProductId(int productId);
    List<ProductCategory> getProductCategoryByCategoryId(int categoryId);

    Boolean productCategoryExist(ProductCategory productCategory);

    int countByProductId(int productId);

    void deleteProductCategory(ProductCategory productCategory);
    void deleteProductCategoryByProductId(int productId);

    List<List<Category>> getCategoryOnProduct(int page, int pageSize);

    List<Integer> getCategoryByProductId(int productId);
}
