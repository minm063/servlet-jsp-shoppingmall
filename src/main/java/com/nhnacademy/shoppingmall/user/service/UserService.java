package com.nhnacademy.shoppingmall.user.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.List;

public interface UserService {

    User getUser(String userId);

    void saveUser(User user);

    void updateUser(User user);

    void updateByUser(User user);

    void deleteUser(String userId);

    User doLogin(String userId, String userPassword);

    List<User> getUsers(User.Auth auth);

    int getTotalCount();

    Page<User> getUserByPage(User.Auth auth, int page, int pageSize);
}
