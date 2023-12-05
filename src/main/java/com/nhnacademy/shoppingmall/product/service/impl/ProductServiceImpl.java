package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductServiceImpl implements ProductService {

    ProductRepository productRepository;

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
        if (productRepository.countProductById(product.getProductId()) != 0) {
            throw new ProductAlreadyExistsException(product.getProductName());
        }
        productRepository.saveProduct(product);
    }

    @Override
    public void updateProduct(Product product) {
        if (productRepository.countProductById(product.getProductId()) == 0) {
            throw new ProductNotFoundException(product.getProductName());
        }
        productRepository.updateProduct(product);
    }

    @Override
    public void deleteProduct(int productId) {
        if (productRepository.countProductById(productId) == 0) {
            throw new ProductNotFoundException(String.valueOf(productId));
        }

        productRepository.deleteProduct(productId);
    }

    @Override
    public List<Product> getProducts() {
        if (productRepository.countAll() == 0) {
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
}
