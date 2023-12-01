package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/logout.do")

public class LogoutController implements BaseController {
    //todo#13-3 로그아웃 구현

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false);

            session.invalidate();

            resp.sendRedirect("redirect:/index.do");
            return "";
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
