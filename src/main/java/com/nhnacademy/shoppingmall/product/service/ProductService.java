package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;

public interface ProductService {
    Product getProduct(int productId);
    Product getProductByProductName(String productName);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void deleteProduct(int productId);

    List<Product> getProducts();

    int getIndex();
}
