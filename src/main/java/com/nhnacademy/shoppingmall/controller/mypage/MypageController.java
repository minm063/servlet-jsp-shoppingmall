package com.nhnacademy.shoppingmall.controller.mypage;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = {"/mypage/menu.do", "/admin/menu.do"})
public class MypageController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        HttpSession session = req.getSession(false);
        User.Auth auth = User.Auth.valueOf(String.valueOf(session.getAttribute("role")));
        if (User.Auth.ROLE_ADMIN.equals(auth)) {
            return "shop/mypage/admin";
        } else {
            return "shop/mypage/user";
        }
    }
}
