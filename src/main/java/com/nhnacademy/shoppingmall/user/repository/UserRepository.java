package com.nhnacademy.shoppingmall.user.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository {
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);

    Optional<User> findById(String userId);

    int save(User user);

    int deleteByUserId(String userId);

    int update(User user);

    int updateByUser(User user);

    int updatePoint(String userId, int pointChanged);

    int findPoint(String userId);

    int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt);

    int updatePointByUserId(String userId, int point);

    int countByUserId(String userId);

    List<User> findUsers(User.Auth auth);

    int totalCount();

    int totalCount(User.Auth auth);

    Page<User> findUserByPage(User.Auth auth, int page, int pageSize);
}
