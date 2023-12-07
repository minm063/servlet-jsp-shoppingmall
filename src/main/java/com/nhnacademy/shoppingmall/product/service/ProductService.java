package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;

public interface ProductService {
    Product getProduct(int productId);

    Product getProductByPath(int productId, String path);

    Product getProductByProductName(String productName);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    List<Product> getProducts();

    int getIndex();

    int getTotalCount();

    int getTotalCountByCategoryId(int categoryId);

    Page<Product> getProductsOnPage(int page, int pageSize);


    Page<Product> getProductsOnPageByPath(int page, int pageSize, String path);

    Page<Product> getProductsOnPageByCategoryId(int page, int pageSize, String path, int categoryId);

}
