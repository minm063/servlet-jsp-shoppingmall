package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import java.util.Optional;

public interface ProductCategoryRepository {
    int saveProductCategory(int productId, int CategoryId);
    int updateProductCategory(int productId, int CategoryId);
    int deleteProductCategory(int productId, int CategoryId);
//    Optional<ProductCategory> readProductCategory(int productId, int CategoryId);

}
