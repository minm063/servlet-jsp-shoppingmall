package com.nhnacademy.shoppingmall.user.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

public interface UserRepository {

    /**
     * parameter {0}, {1}과 일치하는 Optional를 반환한다
     *
     * @param userId       non-null
     * @param userPassword non-null
     * @return Optional
     */
    Optional<User> findByUserIdAndUserPassword(String userId, String userPassword);

    /**
     * parameter와 일치하는 Optional를 반환한다
     *
     * @param userId non-null
     * @return Optional
     */
    Optional<User> findById(String userId);

    /**
     * User 객체를 저장하고 결과값을 반환한다
     * user id에 의해 유저가 조회될 경우 UserAlreadyExistException을 throw한다
     *
     * @param user non-null
     * @return 행 수 또는 0
     */
    int save(User user);

    /**
     * parameter에 따라 저장된 User를 삭제하고 행 수 또는 0을 반환한다
     * 삭제할 User를 찾지 못할 경우 UserNotFoundException을 throw한다
     *
     * @param userId non-null
     * @return 행 수 또는 0
     */
    int deleteByUserId(String userId);

    /**
     * 저장된 User 객체를 parameter 객체로 update하고 행 수 또는 0을 반환한다
     *
     * @param user User
     * @return 행 수 또는 0
     */
    int update(User user);

    /**
     * 특정 column에 대해 parameter 객체로 update하고 행 수 또는 0을 반환한다
     *
     * @param user User
     * @return 행 수 또는 0
     */
    int updateByUser(User user);

    /**
     * user_point 칼럼 value에서 parameter{2}를 뺀 값으로 update하고 행 수 또는 0을 반환한다
     *
     * @param userId       non-null, String value
     * @param pointChanged int value
     * @return 행 수 또는 0
     */
    int updatePoint(String userId, int pointChanged);

    /**
     * parameter에 해당하는 행의 user_point 값을 반환한다
     * @param userId non-null, String value
     * @return user_point value
     */
    int findPoint(String userId);

    /**
     * parameter{0}에 해당하는 row의 latest_login_at column 값을 업데이트하고
     * 행 수 또는 0을 반환한다
     * @param userId non-null, String value
     * @param latestLoginAt LocalDateTime value
     * @return 행 수 또는 0
     */
    int updateLatestLoginAtByUserId(String userId, LocalDateTime latestLoginAt);

    /**
     * parameter{0}에 해당하는 회원 갯수를 반환한다
     *
     * @param userId non-null, String value
     * @return 회원 수
     */
    int countByUserId(String userId);

    /**
     * parameter{0}에 해당하는 회원 리스트를 반환한다
     * @param auth ROLE_ADMIN, ROLE_USER
     * @return 회원 리스트
     */
    List<User> findUsers(User.Auth auth);

    /**
     * users에 저장된 전체 row count를 반환한다
     * @return row count
     */
    int totalCount();

    /**
     * parameter{0}에 해당하는 row count를 반환한다
     * @param auth ROLE_ADMIN, ROLE_USER
     * @return row count
     */
    int totalCount(User.Auth auth);

    /**
     * Auth에 해당하며 page부터 page+pageSize까지의 row를 반환한다
     * @param auth ROLE_ADMIN, ROLE_USER
     * @param page 현재 페이지
     * @param pageSize 한 페이지 사이즈
     * @return Page<User>
     */
    Page<User> findUserByPage(User.Auth auth, int page, int pageSize);
}
