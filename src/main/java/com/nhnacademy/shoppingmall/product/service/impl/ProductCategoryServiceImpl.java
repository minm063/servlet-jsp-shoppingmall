package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import com.nhnacademy.shoppingmall.product.exception.ProductCategoryNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.product.service.ProductCategoryService;
import java.util.List;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    private ProductCategoryRepository productCategoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
    }

    @Override
    public void saveProductCategory(ProductCategory productCategory) {
        if (productCategoryRepository.countByProductId(productCategory.getProductId()) != 0) {
            // already exists exception
        }
        productCategoryRepository.saveProductCategory(productCategory);
    }

    @Override
    public void updateProductCategory(ProductCategory productCategory) {
        if (productCategoryRepository.countByProductId(productCategory.getProductId()) == 0) {
            // not found exception
            throw new ProductCategoryNotFoundException(String.valueOf(productCategory.getProductId()));
        }
        productCategoryRepository.updateProductCategory(productCategory);

    }

    @Override
    public List<ProductCategory> getProductCategoryByProductId(int productId) {
        // if count==0 throw not exists
        if (productCategoryRepository.countByProductId(productId) == 0) {
            throw new ProductCategoryNotFoundException(String.valueOf(productId));
        }
        return productCategoryRepository.findByProductId(productId);
    }

    @Override
    public List<ProductCategory> getProductCategoryByCategoryId(int categoryId) {
        if (productCategoryRepository.countByCategoryId(categoryId) == 0) {
            throw new ProductCategoryNotFoundException(String.valueOf(categoryId));
        }
        return productCategoryRepository.findByCategoryId(categoryId);
    }

    @Override
    public Boolean productCategoryExist(ProductCategory productCategory) {
        return productCategoryRepository.productCategoryExist(productCategory) != 0;
    }

    @Override
    public int countByProductId(int productId) {
        return countByProductId(productId);
    }

    @Override
    public void deleteProductCategory(ProductCategory productCategory) {
        if (productCategoryRepository.countByCategoryId(productCategory.getCategoryId()) == 0) {
            throw new ProductCategoryNotFoundException(String.valueOf(productCategory.getCategoryId()));
        }
        productCategoryRepository.deleteProductCategory(productCategory);
    }

    @Override
    public void deleteProductCategoryByProductId(int productId) {
        if (productCategoryRepository.countByProductId(productId) == 0) {
            throw new ProductCategoryNotFoundException(String.valueOf(productId));
        }
        productCategoryRepository.deleteProductCategoryByProductId(productId);

    }
}
