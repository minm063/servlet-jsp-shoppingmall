package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import java.util.List;

public interface ProductCategoryRepository {
    int saveProductCategory(ProductCategory productCategory);

    int updateProductCategory(ProductCategory productCategory);

    int countByProductId(int productId);

    int countByCategoryId(int categoryId);

    List<ProductCategory> findByProductId(int productId);

    List<ProductCategory> findByCategoryId(int categoryId);

    int productCategoryExist(ProductCategory productCategory);

    int deleteProductCategory(ProductCategory productCategory);

    int deleteProductCategoryByProductId(int productId);
}
