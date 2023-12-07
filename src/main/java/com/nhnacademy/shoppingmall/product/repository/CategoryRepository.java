package com.nhnacademy.shoppingmall.product.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import java.util.List;
import java.util.Optional;

public interface CategoryRepository {
    int saveCategory(Category category);

    int updateCategory(Category category);

    int deleteCategory(int categoryId);

    Optional<Category> findCategoryByName(String categoryName);

    Optional<Category> findCategoryById(int categoryId);

    int countByCategoryId(int categoryId);

    List<Category> findCategories();

    int totalCount();

    List<Category> findCategoryByProductId(int productId);

    Page<Category> findCategoryOnPage(int page, int pageSize);
}
