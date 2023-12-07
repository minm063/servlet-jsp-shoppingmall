package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.ProductAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.ProductNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductService;
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


}
