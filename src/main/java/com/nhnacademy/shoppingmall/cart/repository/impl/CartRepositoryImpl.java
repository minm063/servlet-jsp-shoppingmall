package com.nhnacademy.shoppingmall.cart.repository.impl;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.CartRepository;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class CartRepositoryImpl implements CartRepository {
    @Override
    public int save(Cart cart) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into cart (quantity, date_created, product_id, user_id) VALUES (?,?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, cart.getQuantity());
            preparedStatement.setObject(2, cart.getDateCreated());
            preparedStatement.setInt(3, cart.getProductId());
            preparedStatement.setString(4, cart.getUserId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
