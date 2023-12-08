package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.OrderDetail;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.math.BigDecimal;
import java.util.List;

public interface ProductService {
    Product getProduct(int productId);

    Product getProductByPath(int productId, String path);

    Product getProductByProductName(String productName);

    void saveProduct(Product product);

    void updateProduct(Product product);

    void updateProduct(int productId, String productName, BigDecimal unitCost, int stock, String description,
                       String productImage, String thumbnail);

    Boolean updateProductStock(List<Product> productList);

    void deleteProduct(int productId);

    List<Product> getProducts();

    int getIndex();

    int getTotalCount();

    int getTotalCountBySearch(String search);

    int getTotalCountByCategoryId(int categoryId);

    Page<Product> getProductsOnPage(int page, int pageSize);


    Page<Product> getProductsOnPageByPath(int page, int pageSize, String path);

    Page<Product> getProductsOnPageByCategoryId(int page, int pageSize, String path, int categoryId);

    Page<Product> getProductsOnPageBySearch(int page, int pageSize, String path, String search);

    List<Product> getProducts(List<Cart> cartList);

    List<List<Product>> getProductsByProductId(List<List<OrderDetail>> orderDetailList);
}
