package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import com.nhnacademy.shoppingmall.product.exception.ProductCategoryNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import com.nhnacademy.shoppingmall.product.service.ProductCategoryService;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryServiceImpl implements ProductCategoryService {

    private final ProductCategoryRepository productCategoryRepository;
    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    public ProductCategoryServiceImpl(ProductCategoryRepository productCategoryRepository,
                                      ProductRepository productRepository, CategoryRepository categoryRepository) {
        this.productCategoryRepository = productCategoryRepository;
        this.productRepository = productRepository;
        this.categoryRepository = categoryRepository;
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

    public List<List<Category>> getCategoryOnProduct(int page, int pageSize) {
        /**
         * product를 내부에서 일단 page size만큼으로 List<Product>로 가져
         * 반복문으로 List<Category>를 가져와
         * 그걸 List<List<Category>>.add 해서 리턴하면
         * 카테고리를 가질 수 있지
         */


        List<List<Category>> categoryList = new ArrayList<>();

        if (productRepository.totalCount() == 0) {
            return new ArrayList<>();
        }

        Page<Product> productList = productRepository.findProductsOnPage(page, pageSize);
        for (Product product : productList.getContent()) {
            if (productCategoryRepository.countByProductId(product.getProductId()) == 0) {
                categoryList.add(new ArrayList<>());
            } else {
                categoryList.add(categoryRepository.findCategoryByProductId(product.getProductId()));
            }
        }

        return categoryList;
    }

    @Override
    public List<Integer> getCategoryByProductId(int productId) {
        List<Integer> categoryList = new ArrayList<>();
        if (productCategoryRepository.countByProductId(productId) != 0) {
            List<Category> categories = categoryRepository.findCategoryByProductId(productId);

            for (int i = 0; i < categories.size(); i++) {
                categoryList.add(categories.get(i).getCategoryId());
            }
            for (int i = 0; i < 3 - categories.size(); i++) {
                categoryList.add(0);
            }
        }

        return categoryList;
    }
}
