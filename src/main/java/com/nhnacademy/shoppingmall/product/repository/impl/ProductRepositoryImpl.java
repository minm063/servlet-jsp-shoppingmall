package com.nhnacademy.shoppingmall.product.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.ProductRepository;
import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ProductRepositoryImpl implements ProductRepository {
    @Override
    public Optional<Product> findByProductName(String productName) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select product_id, product_number, product_name, unit_cost, description, product_image, thumbnail from product where product_name=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productName);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_number"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("unit_cost"),
                        resultSet.getString("description"),
                        resultSet.getString("product_image"),
                        resultSet.getString("thumbnail")
                );

                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public Optional<Product> findByProductNumber(String productNumber) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select product_id, product_number, product_name, unit_cost, description, product_image, thumbnail from product where product_number=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, productNumber);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_number"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("unit_cost"),
                        resultSet.getString("description"),
                        resultSet.getString("product_image"),
                        resultSet.getString("thumbnail")
                );

                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int saveProduct(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "insert into product (product_number, product_name, unit_cost, description, product_image, thumbnail) values (?,?,?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getProductNumber());
            preparedStatement.setString(2, product.getProductName());
            preparedStatement.setBigDecimal(3, product.getUnitCost());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getProductImage());
            preparedStatement.setString(6, product.getThumbnail());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateProduct(Product product) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "update product set product_number=?, product_name=?, unit_cost=?, description=?, product_image=?, thumbnail=? where product_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, product.getProductNumber());
            preparedStatement.setString(2, product.getProductNumber());
            preparedStatement.setBigDecimal(3, product.getUnitCost());
            preparedStatement.setString(4, product.getDescription());
            preparedStatement.setString(5, product.getProductImage());
            preparedStatement.setString(6, product.getThumbnail());
            preparedStatement.setInt(7, product.getProductId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteProduct(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "delete from product where product_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public List<Product> findProducts() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select product.* from product";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> list = new ArrayList<>();

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_number"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("unit_cost"),
                        resultSet.getString("description"),
                        resultSet.getString("product_image"),
                        resultSet.getString("thumbnail")
                );
                list.add(product);
            }
            return list;
        } catch (SQLException e) {
            return new ArrayList<>();
        }
    }

    @Override
    public int totalCount() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from product";

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
    public int totalCountByCategoryId(int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*)\n" +
                "from product\n" +
                "         join product_category pc on product.product_id = pc.product_id\n" +
                "         join category c on pc.category_id = c.category_id\n" +
                "where c.category_id=?\n";

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
    public int countProductById(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from product where product_id=?";

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
    public Optional<Product> findByProductId(int productId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select product_id, product_number, product_name, unit_cost, description, product_image, thumbnail from product where product_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_number"),
                        resultSet.getString("product_name"),
                        resultSet.getBigDecimal("unit_cost"),
                        resultSet.getString("description"),
                        resultSet.getString("product_image"),
                        resultSet.getString("thumbnail")
                );

                return Optional.of(product);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int findIndex() {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select last_insert_id()";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            ResultSet result = preparedStatement.executeQuery();
            if (result.next()) {
                return result.getInt(1);
            }
        } catch (SQLException e) {
        }
        return 0;
    }

    @Override
    public Page<Product> findProductsOnPage(int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from product order by product_id limit ?, ?";
        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, offset);
            preparedStatement.setInt(2, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>(pageSize);

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_number"),
                        resultSet.getString("product_name"),
                        new BigDecimal(resultSet.getString("unit_cost")),
                        resultSet.getString("description"),
                        resultSet.getString("product_image"),
                        resultSet.getString("thumbnail")
                );

                productList.add(product);
            }

            long total = 0;
            if (!productList.isEmpty()) {
                total = this.totalCount();
            }
            return new Page<>(productList, total);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Page<Product> findProductsOnPageByCategory(int page, int pageSize, int categoryId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select product.*\n" +
                "from product\n" +
                "         join product_category pc on product.product_id = pc.product_id\n" +
                "         join category c on pc.category_id = c.category_id\n" +
                "where c.category_id=? order by product_id limit ?, ?\n";

        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, categoryId);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);

            ResultSet resultSet = preparedStatement.executeQuery();
            List<Product> productList = new ArrayList<>(pageSize);

            while (resultSet.next()) {
                Product product = new Product(
                        resultSet.getInt("product_id"),
                        resultSet.getString("product_number"),
                        resultSet.getString("product_name"),
                        new BigDecimal(resultSet.getString("unit_cost")),
                        resultSet.getString("description"),
                        resultSet.getString("product_image"),
                        resultSet.getString("thumbnail")
                );

                productList.add(product);
            }

            long total = 0;
            if (!productList.isEmpty()) {
                total = this.totalCount();
            }
            return new Page<>(productList, total);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }    }


}
