package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findByProductName(String productName);

    Optional<Product> findByProductNumber(String productNumber);

    int saveProduct(Product product);

    int updateProduct(Product product);

    int deleteProduct(int productId);

    int countProductById(int productId);

    int totalCount();

    int totalCountByCategoryId(int categoryId);

    List<Product> findProducts();

    Optional<Product> findByProductId(int productId);

    int findIndex();

    Page<Product> findProductsOnPage(int page, int pageSize);

    Page<Product> findProductsOnPageByCategory(int page, int pageSize, int categoryId);
}
