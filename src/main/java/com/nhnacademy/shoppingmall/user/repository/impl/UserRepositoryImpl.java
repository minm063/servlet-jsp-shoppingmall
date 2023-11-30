package com.nhnacademy.shoppingmall.user.repository.impl;

import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.UserRepository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Objects;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class UserRepositoryImpl implements UserRepository {

    @Override
    public Optional<User> findByUserIdAndUserPassword(String userId, String userPassword) {
        /*todo#3-1 회원의 아이디와 비밀번호를 이용해서 조회하는 코드 입니다.(로그인)
          해당 코드는 SQL Injection이 발생합니다. SQL Injection이 발생하지 않도록 수정하세요.
         */
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id=? and user_password =?";

        log.debug("sql:{}", sql);

        try (PreparedStatement psmt = connection.prepareStatement(sql)) {
            psmt.setString(1, userId);
            psmt.setString(2, userPassword);

            ResultSet rs = psmt.executeQuery();
            if (rs.next()) {
                User user = new User(
                        rs.getString("user_id"),
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        Objects.nonNull(rs.getTimestamp("created_at")) ?
                                rs.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return Optional.empty();
    }

    @Override
    public Optional<User> findById(String userId) {
        //todo#3-2 회원조회
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "select user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at from users where user_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            ResultSet rs = preparedStatement.executeQuery();
            log.info("rs: {}", rs);
            if (rs.next()) {
                User user = new User(
                        userId,
                        rs.getString("user_name"),
                        rs.getString("user_password"),
                        rs.getString("user_birth"),
                        User.Auth.valueOf(rs.getString("user_auth")),
                        rs.getInt("user_point"),
                        Objects.nonNull(rs.getTimestamp("created_at")) ?
                                rs.getTimestamp("created_at").toLocalDateTime() : null,
                        Objects.nonNull(rs.getTimestamp("latest_login_at")) ?
                                rs.getTimestamp("latest_login_at").toLocalDateTime() : null
                );
                return Optional.of(user);
            }

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return Optional.empty();
    }

    @Override
    public int save(User user) {
        //todo#3-3 회원등록, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "insert into users (user_id, user_name, user_password, user_birth, user_auth, user_point, created_at, latest_login_at) values (?, ?, ?, ?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserPassword());
            preparedStatement.setString(4, user.getUserBirth());
            preparedStatement.setString(5, user.getUserAuth().toString());
            preparedStatement.setInt(6, user.getUserPoint());
            preparedStatement.setObject(7, user.getCreatedAt());
            preparedStatement.setObject(8, user.getLatestLoginAt());

            int result = preparedStatement.executeUpdate();
            log.info("save row: {}", result);
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int deleteByUserId(String userId) {
        //todo#3-4 회원삭제, executeUpdate()을 반환합니다.
        String sql = "delete from users where user_id=?";
        Connection connection = DbConnectionThreadLocal.getConnection();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int update(User user) {
        //todo#3-5 회원수정, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql =
                "update users set user_name=?, user_password=?, user_birth=?, user_auth=?, user_point=? where user_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, user.getUserName());
            preparedStatement.setString(2, user.getUserPassword());
            preparedStatement.setString(3, user.getUserBirth());
            preparedStatement.setString(4, user.getUserAuth().toString());
            preparedStatement.setInt(5, user.getUserPoint());
            preparedStatement.setString(6, user.getUserId());

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt) {
        //todo#3-6, 마지막 로그인 시간 업데이트, executeUpdate()을 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "update users set latest_login_at=? where user_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setObject(1, latestLoginAt);
            preparedStatement.setString(2, userId);

            int result = preparedStatement.executeUpdate();
            return result;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @Override
    public int countByUserId(String userId) {
        //todo#3-7 userId와 일치하는 회원의 count를 반환합니다.
        Connection connection = DbConnectionThreadLocal.getConnection();
        String sql = "select count(*) from users where user_id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userId);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                int count = resultSet.getInt(1);
                return count;
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return 0;
    }
}
