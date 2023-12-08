package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public Product getProductByProductName(String productName) {
        Optional<Product> product = productRepository.findByProductName(productName);
        if (product.isPresent()) {
            return product.get();
        }

        return null;
    }

    @Override
    public void saveProduct(Product product) {
        if (productRepository.countById(product.getProductId()) != 0) {
            throw new ProductAlreadyExistsException(product.getProductName());
        }
        productRepository.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.countById(product.getProductId()) == 0) {
            throw new ProductNotFoundException(product.getProductName());
        }
        productRepository.updateProduct(product);
    }

    @Override
    public void updateProduct(int productId, String productName, BigDecimal unitCost, int stock, String description,
                              String productImage, String thumbnail) {

        if (productRepository.countById(productId) == 0) {
            throw new ProductNotFoundException(productName);
        }
        productRepository.updateProduct(productId, productName, unitCost, stock, description, productImage, thumbnail);
    }

    @Override
    public void deleteProduct(int productId) {
        if (productRepository.countById(productId) == 0) {
            throw new ProductNotFoundException(String.valueOf(productId));
        }

        productRepository.deleteProduct(productId);
    }

    @Override
    public List<Product> getProducts() {
        if (productRepository.totalCount() == 0) {
            return new ArrayList<>();
        }
        return productRepository.findProducts();
    }

    @Override
    public Product getProduct(int productId) {
        Optional<Product> product = productRepository.findByProductId(productId);
        if (product.isPresent()) {
            return product.get();
        }
        return null;
    }

    @Override
    public Product getProductByPath(int productId, String path) {
        Optional<Product> product = productRepository.findByProductId(productId);
        if (product.isPresent()) {
            Product temp = product.get();

            temp.setProductImage(path + temp.getProductImage());
            temp.setThumbnail(path + temp.getThumbnail());
            log.info(temp.getThumbnail());
            log.info(temp.getProductImage());
            return temp;
        }
        return null;
    }

    @Override
    public int getIndex() {
        return productRepository.findIndex();
    }

    @Override
    public int getTotalCount() {
        return productRepository.totalCount();
    }

    @Override
    public int getTotalCountByCategoryId(int categoryId) {
        return productRepository.totalCountByCategoryId(categoryId);
    }

    @Override
    public int getTotalCountBySearch(String search) {
        return productRepository.countBySearch(search);
    }

    @Override
    public Page<Product> getProductsOnPage(int page, int pageSize) {
        return productRepository.findProductsOnPage(page, pageSize);
    }

    // index.jsp에서 product(경로를 붙인 이미지로 수정하여 반환)
    public Page<Product> getProductsOnPageByPath(int page, int pageSize, String path) {
        if (productRepository.totalCount() == 0) {
            throw new ProductNotFoundException(String.valueOf(page));
        }

        Page<Product> productsOnPage = productRepository.findProductsOnPage(page, pageSize);

        for (Product product : productsOnPage.getContent()) {
            product.setProductImage(path + product.getProductImage());
            product.setThumbnail(path + product.getThumbnail());
        }

        return productsOnPage;
    }

    @Override
    public Page<Product> getProductsOnPageByCategoryId(int page, int pageSize, String path, int categoryId) {
        if (productRepository.totalCountByCategoryId(categoryId) == 0) {
            return new Page<>(new ArrayList<>(), 0);
        }
        Page<Product> productsOnPage = productRepository.findProductsOnPageByCategory(page, pageSize, categoryId);

        for (Product product : productsOnPage.getContent()) {
            product.setProductImage(path + product.getProductImage());
            product.setThumbnail(path + product.getThumbnail());
        }

        return productsOnPage;
    }

    @Override
    public Page<Product> getProductsOnPageBySearch(int page, int pageSize, String path, String search) {
        if (productRepository.countBySearch(search) == 0) {
            return new Page<>(new ArrayList<>(), 0);
        }
        Page<Product> productPage = productRepository.findProductBySearch(page, pageSize, search);
        for (Product product : productPage.getContent()) {
            product.setProductImage(path + product.getProductImage());
            product.setThumbnail(path + product.getThumbnail());
        }

        return productPage;
    }

    @Override
    public List<Product> getProducts(List<Cart> cartList) {
        List<Product> productList = new ArrayList<>();
        for (Cart cart : cartList) {
            Optional<Product> product = productRepository.findByProductId(cart.getProductId());
            if (product.isPresent()) {
                productList.add(product.get());
            }
        }
        return productList;
    }

    @Override
    public void updateProductStock(List<Product> productList) {
        for (Product product : productList) {
            int prevStock = productRepository.findStock(product.getProductId());
            productRepository.updateProductStock(product.getProductId(), prevStock - product.getStock());
        }
    }
}
