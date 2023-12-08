package com.nhnacademy.shoppingmall.point.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.point.domain.Point;
import com.nhnacademy.shoppingmall.point.repository.PointRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;


@Slf4j
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

    @Override
    public Page<Point> findPointByUserIdOnPage(String userId, int page, int pageSize) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select user_point.* from user_point where user_id=? order by point_date desc limit ?,?";

        int offset = (page - 1) * pageSize;
        int limit = pageSize;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);
            preparedStatement.setInt(2, offset);
            preparedStatement.setInt(3, limit);

            List<Point> pointList = new ArrayList<>();
            ResultSet resultSet = preparedStatement.executeQuery();
            while (resultSet.next()) {
                Point point = new Point(
                        resultSet.getInt("point_id"),
                        resultSet.getInt("point_changed"),
                        resultSet.getTimestamp("point_date").toLocalDateTime(),
                        userId
                );
                pointList.add(point);
            }

            long total = 0;
            if (!pointList.isEmpty()) {
                total = this.totalCount(userId);
            }
            return new Page<>(pointList, total);

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int totalCount(String userId) {
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from user_point where user_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                log.info("point total count: {}", count);
                return count;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
