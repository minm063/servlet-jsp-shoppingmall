package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.GET, value = "/signup.do")
public class SignupController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        try {
            HttpSession session = req.getSession(false);

            if (Objects.nonNull(session) && Objects.nonNull(session.getAttribute("user_id"))) {
                resp.sendRedirect("redirect:/index.do");
            }
            return "shop/signup/signup_form";

        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}
