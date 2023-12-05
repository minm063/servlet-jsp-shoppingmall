package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.exception.UserNotFoundException;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private static final int INACTIVE_INTERVAL_TIME = 3600;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String id = req.getParameter("user_id");
        String pw = req.getParameter("user_password");
        User user = userService.getUser(id);
        try {
            userService.doLogin(id, pw);

            HttpSession session = req.getSession(true);
            session.setMaxInactiveInterval(INACTIVE_INTERVAL_TIME);
            session.setAttribute("user_id", id);
            session.setAttribute("user_name", user.getUserName());

            User.Auth role = user.getUserAuth();
            String compactRole;
            if (role.equals(User.Auth.ROLE_USER)) {
                compactRole = "user";
            } else {
                compactRole = "admin";
            }

            session.setAttribute("role", role);
            session.setAttribute("compactRole", compactRole);

            return "shop/main/index";
//            return "redirect:/index.do";
        } catch (UserNotFoundException e) {
            return "redirect:/login.do";
        }

    }
}
