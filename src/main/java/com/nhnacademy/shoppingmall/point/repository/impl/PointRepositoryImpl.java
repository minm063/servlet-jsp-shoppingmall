package com.nhnacademy.shoppingmall.point.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.point.domain.Point;
import com.nhnacademy.shoppingmall.point.repository.PointRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PointRepositoryImpl implements PointRepository {

    @Override
    public int save(Point point) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "insert into user_point (point_changed, point_date, user_id) VALUES (?,?,?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, point.getPointChanged());
            preparedStatement.setObject(2, point.getPointDate());
            preparedStatement.setString(3, point.getUserId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }
}
