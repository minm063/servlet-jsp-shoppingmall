package com.nhnacademy.shoppingmall.product.service.impl;

import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.exception.CategoryAlreadyExistsException;
import com.nhnacademy.shoppingmall.product.exception.CategoryNotFoundException;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;
import com.nhnacademy.shoppingmall.product.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

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
        if (categoryRepository.countAll() == 0) {
            return new ArrayList<>();
        }
        return categoryRepository.findCategories();
    }
}
