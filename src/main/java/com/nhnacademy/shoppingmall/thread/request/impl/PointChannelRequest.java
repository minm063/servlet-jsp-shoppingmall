package com.nhnacademy.shoppingmall.thread.request.impl;

import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.transaction.DbConnectionThreadLocal;
import com.nhnacademy.shoppingmall.point.domain.Point;
import com.nhnacademy.shoppingmall.point.repository.impl.PointRepositoryImpl;
import com.nhnacademy.shoppingmall.point.service.PointService;
import com.nhnacademy.shoppingmall.point.service.impl.PointServiceImpl;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.ChannelRequest;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.sql.Connection;
import java.time.LocalDateTime;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class PointChannelRequest extends ChannelRequest {

    private final PointService pointService = new PointServiceImpl(new PointRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());
    private String userId;
    private LocalDateTime pointDate;
    private int pointChanged;

    public PointChannelRequest(String userId, LocalDateTime pointDate, int pointChanged) {
        this.userId =userId;
        this.pointChanged = pointChanged;
        this.pointDate = pointDate;
    }

    @Override
    public void execute() {
        DbConnectionThreadLocal.initialize();
        //todo#14-5 포인트 적립구현, connection은 point적립이 완료되면 반납합니다.
        // 로그 저장
        // 포인트 업데이트

//        userService.updatePoint(userId);
//        pointService.save(point); // 주문 금액의 10% => unit cost sum
//        cartService.delete
        pointService.save(new Point(pointChanged, pointDate, userId));
        userService.updatePoint(userId, pointChanged);

        log.debug("pointChannel execute");
        DbConnectionThreadLocal.reset();
    }
}
