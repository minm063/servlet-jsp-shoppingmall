package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.CategoryRepository;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CategoryRepositoryImpl implements CategoryRepository {
    @Override
    public int saveCategory(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into category (category_name) values (?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, category.getCategoryName());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateCategory(Category category) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update category set category_name=? where category_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, category.getCategoryName());
            preparedStatement.setInt(2, category.getCategoryId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteCategory(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from category where category_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<Category> findCategoryByName(String categoryName) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category where category_name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, categoryName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name")
                );

                return Optional.of(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int countByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from category where category_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count;
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    @Override
    public List<Category> findCategories() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category order by category_id";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> list = new ArrayList<>();

            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name")
                );
                list.add(category);
            }
            return list;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public int totalCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from category";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count;
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    @Override
    public Optional<Category> findCategoryById(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category where category_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name")
                );

                return Optional.of(category);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<Category> findCategoryByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select category.*\n" +
                "from category\n" +
                "         join product_category pc on category.category_id = pc.category_id\n" +
                "         join product p on pc.product_id = p.product_id\n" +
                "where p.product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            List<Category> categoryList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Category category = new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name")
                );
                categoryList.add(category);
            }
            return categoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Category> findCategoryOnPage(int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from category order by category_id limit ?, ?";
        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Category> categoryList = new ArrayList<>(pageSize);

            while (resultSet.next()) {
                Category category= new Category(
                        resultSet.getInt("category_id"),
                        resultSet.getString("category_name")
                );

                categoryList.add(category);
            }

            long total = 0;
            if (!categoryList.isEmpty()) {
                total = this.totalCount();
            }
            return new Page<>(categoryList, total);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
