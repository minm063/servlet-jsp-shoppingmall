package com.nhnacademy.shoppingmall.product.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import java.util.List;

public interface CategoryService {
    void saveCategory(Category category);

    void updateCategory(Category category);

    void deleteCategory(int categoryId);

    Category getCategory(int categoryId);

    List<Category> getCategories();

    Page<Category> getCategoryOnPage(int page, int pageSize);

    int getTotalCount();
}
