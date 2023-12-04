package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/signupAction.do")
public class SignupPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        String userId = req.getParameter("user_id");
        String userName = req.getParameter("user_name");
        String userPw = req.getParameter("user_password");
        String userBirth = req.getParameter("user_birth");
        userBirth = userBirth.replace("-", "");
        User.Auth auth = User.Auth.ROLE_USER;
        int userPoint = 1_000_000;
        LocalDateTime createdAt = LocalDateTime.now();
        LocalDateTime latestLoginAt = null;


        User user = new User(userId, userName, userPw, userBirth, auth, userPoint, createdAt, latestLoginAt);
        userService.saveUser(user);

        return "shop/login/login_form";
    }
}
