package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.product.domain.ProductCategory;
import com.nhnacademy.shoppingmall.product.repository.ProductCategoryRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ProductCategoryRepositoryImpl implements ProductCategoryRepository {
    @Override
    public int saveProductCategory(ProductCategory productCategory) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into product_category values (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productCategory.getProductId());
            preparedStatement.setInt(2, productCategory.getCategoryId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateProductCategory(ProductCategory productCategory) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update product_category set category_id = ? where product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productCategory.getCategoryId());
            preparedStatement.setInt(2, productCategory.getProductId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from product_category where product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

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
    public int countByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from product_category where category_id = ?";

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
    public List<ProductCategory> findByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select category_id from product_category where product_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            List<ProductCategory> productCategoryList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(productId, resultSet.getInt("category_id"));
                productCategoryList.add(productCategory);
            }
            return productCategoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<ProductCategory> findByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select product_id from product_category where category_id = ?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);

            List<ProductCategory> productCategoryList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                ProductCategory productCategory = new ProductCategory(resultSet.getInt("product_id"), categoryId);
                productCategoryList.add(productCategory);
            }
            return productCategoryList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int productCategoryExist(ProductCategory productCategory) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from product_category where product_id=? and category_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productCategory.getProductId());
            preparedStatement.setInt(2, productCategory.getCategoryId());

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
    public int deleteProductCategory(ProductCategory productCategory) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from product_category where product_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productCategory.getCategoryId());
            preparedStatement.setInt(2, productCategory.getCategoryId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
    @Override
    public int deleteProductCategoryByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from product_category where product_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
