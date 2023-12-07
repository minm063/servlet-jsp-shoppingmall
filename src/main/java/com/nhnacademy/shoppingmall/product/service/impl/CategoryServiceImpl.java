package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.exception.CategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import com.nhnacademy.shoppingmall.product.service.ProductCategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;
//    private final ProductCategoryRepository productCategoryRepository;
//
//    public CategoryServiceImpl(CategoryRepository categoryRepository,
//                               ProductCategoryRepository productCategoryRepository) {
//        this.categoryRepository = categoryRepository;
//        this.productCategoryRepository = productCategoryRepository;
//    }

    public CategoryServiceImpl(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void saveCategory(Category category) {
        if (categoryRepository.countByCategoryId(category.getCategoryId()) != 0) {
            throw new CategoryAlreadyExistsException(category.getCategoryName());
        }
        categoryRepository.saveCategory(category);
    }

    @Override
    public void updateCategory(Category category) {
        if (categoryRepository.countByCategoryId(category.getCategoryId()) == 0) {
            throw new CategoryNotFoundException(category.getCategoryName());
        }
        categoryRepository.updateCategory(category);
    }

    @Override
    public void deleteCategory(int categoryId) {
        if (categoryRepository.countByCategoryId(categoryId) == 0) {
            throw new CategoryNotFoundException(String.valueOf(categoryId));
        }

        categoryRepository.deleteCategory(categoryId);
    }

    @Override
    public Category getCategory(int categoryId) {
        Optional<Category> category = categoryRepository.findCategoryById(categoryId);
        if (category.isPresent()) {
            return category.get();
        }
        return null;
    }

    @Override
    public List<Category> getCategories() {
        if (categoryRepository.totalCount() == 0) {
            return new ArrayList<>();
        }
        return categoryRepository.findCategories();
    }

    @Override
    public Page<Category> getCategoryOnPage(int page, int pageSize) {
        if (categoryRepository.totalCount() == 0) {
            throw new CategoryNotFoundException(String.valueOf(page));
        }

        return categoryRepository.findCategoryOnPage(page, pageSize);
    }

    @Override
    public int getTotalCount() {
        return categoryRepository.totalCount();
    }
}
