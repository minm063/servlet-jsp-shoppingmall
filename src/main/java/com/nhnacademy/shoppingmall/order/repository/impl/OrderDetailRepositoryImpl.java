package com.nhnacademy.shoppingmall.order.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.order.domain.OrderDetail;
import com.nhnacademy.shoppingmall.order.repository.OrderDetailRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailRepositoryImpl implements OrderDetailRepository {
    @Override
    public int save(OrderDetail orderDetail) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into order_detail values(?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderDetail.getOrderId());
            preparedStatement.setInt(2, orderDetail.getProductId());
            preparedStatement.setInt(3, orderDetail.getQuantity());
            preparedStatement.setInt(4, orderDetail.getUnitCost());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public Optional<OrderDetail> findOrderDetailByProductId(int productId, int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select quantity, unit_cost from product join cart c on product.product_id = c.product_id where c.product_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, productId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        orderId,
                        productId,
                        resultSet.getInt("quantity"),
                        resultSet.getInt("unit_cost")
                );
                return Optional.of(orderDetail);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public List<OrderDetail> findOrderDetailByOrderId(int orderId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select * from order_detail where order_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, orderId);

            List<OrderDetail> orderDetailList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                OrderDetail orderDetail = new OrderDetail(
                        orderId,
                        resultSet.getInt("product_id"),
                        resultSet.getInt("quantity"),
                        resultSet.getInt("unit_cost")
                );
                orderDetailList.add(orderDetail);
            }
            return orderDetailList;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
