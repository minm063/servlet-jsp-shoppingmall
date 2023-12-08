package com.nhnacademy.shoppingmall.controller.auth;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.point.domain.Point;
import com.nhnacademy.shoppingmall.point.repository.impl.PointRepositoryImpl;
import com.nhnacademy.shoppingmall.point.service.PointService;
import com.nhnacademy.shoppingmall.point.service.impl.PointServiceImpl;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.impl.PointChannelRequest;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.time.Duration;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/loginAction.do")
public class LoginPostController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final PointService pointService = new PointServiceImpl(new PointRepositoryImpl());
    private static final int INACTIVE_INTERVAL_TIME = 3600;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        //todo#13-2 로그인 구현, session은 60분동안 유지됩니다.
        String userId = req.getParameter("user_id");
        String userPassword = req.getParameter("user_password");

        if (Objects.isNull(userService.getUser(userId))) {
            return "redirect:/login.do";
        }
        User user = userService.getUser(userId);
        LocalDateTime previous = user.getLatestLoginAt();
        User.Auth role = user.getUserAuth();

        if (previous.toLocalDate().plusDays(1).equals(LocalDate.now())) {
            ServletContext servletContext = req.getServletContext();
            RequestChannel requestChannel = (RequestChannel) servletContext.getAttribute("requestChannel");
            try {
                requestChannel.addRequest(new PointChannelRequest(userId, LocalDateTime.now(), 100_000));
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            // update point by userId
        }

        userService.doLogin(userId, userPassword);
        HttpSession session = req.getSession(true);

        session.setMaxInactiveInterval(INACTIVE_INTERVAL_TIME);
        session.setAttribute("userId", userId);
        session.setAttribute("role", role);
//            return "shop/main/index";
        return "redirect:/index.do";

    }
}
