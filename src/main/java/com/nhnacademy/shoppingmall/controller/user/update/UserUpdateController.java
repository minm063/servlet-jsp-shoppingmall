package com.nhnacademy.shoppingmall.controller.user.update;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/update.do")
public class UserUpdateController implements BaseController {

    UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);

        String userId = String.valueOf(session.getAttribute("userId"));
        log.info("user id : {}", userId);
        User user = userService.getUser(userId);

        try {
            SimpleDateFormat format = new SimpleDateFormat("yyyyMMdd");
            SimpleDateFormat toString = new SimpleDateFormat("yyyy-MM-dd");
            Date date = format.parse(user.getUserBirth());
            String birth = toString.format(date);

            req.setAttribute("birth", birth);
        } catch (ParseException e) {
            req.setAttribute("birth", user.getUserBirth());
        }

        req.setAttribute("user", user);

        return "/shop/mypage/user/update";
    }
}
