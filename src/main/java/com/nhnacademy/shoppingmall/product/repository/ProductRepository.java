package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;

public interface ProductRepository {
    Optional<Product> findByProductName(String productName);

    Optional<Product> findByProductNumber(String productNumber);

    int saveProduct(Product product);

    int updateProduct(Product product);

    int updateProduct(int productId, String productName, BigDecimal unitCost, int stock, String description,
                      String productImage, String thumbnail);

    int updateProductStock(int productId, int stock);

    int deleteProduct(int productId);

    int countById(int productId);

    int totalCount();

    int totalCountByCategoryId(int categoryId);

    List<Product> findProducts();

    Optional<Product> findByProductId(int productId);

    int findIndex();

    Page<Product> findProductsOnPage(int page, int pageSize);

    Page<Product> findProductsOnPageByCategory(int page, int pageSize, int categoryId);

    int countBySearch(String search);

    Page<Product> findProductBySearch(int page, int pageSize, String search);

    int findStock(int productId);
}
