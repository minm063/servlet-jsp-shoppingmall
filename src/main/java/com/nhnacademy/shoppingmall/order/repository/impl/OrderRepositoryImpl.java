package com.nhnacademy.shoppingmall.order.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Optional;

public class OrderRepositoryImpl implements OrderRepository {

    @Override
    public int save(Order order) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into `order` (order_date, ship_date, user_id, address_id) values (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, order.getOrderDate());
            preparedStatement.setObject(2, order.getShipDate());
            preparedStatement.setString(3, order.getUserId());
            preparedStatement.setInt(4, order.getAddressId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int findId() {
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
    public Optional<Order> findOrder(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from `order` where order_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                Order order = new Order(orderId,
                        resultSet.getTimestamp("order_date").toLocalDateTime(),
                        resultSet.getTimestamp("ship_date").toLocalDateTime(),
                        resultSet.getString("user_id"),
                        resultSet.getInt("address_id")
                );
                return Optional.of(order);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }
}
