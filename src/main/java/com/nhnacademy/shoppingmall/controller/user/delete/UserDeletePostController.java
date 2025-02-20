package com.nhnacademy.shoppingmall.controller.user.delete;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/mypage/deleteAction.do")
public class UserDeletePostController implements BaseController {

    UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession session = req.getSession(false);
        String deleteText = req.getParameter("deleteText");
        String userId = String.valueOf(session.getAttribute("userId"));

        log.info(userId);

        if (Objects.nonNull(userId) && "breakaway".equals(deleteText)) {
            log.info("탈퇴합니다");
            userService.deleteUser(userId);
            session.invalidate();
            return "redirect:/index.do";
        }
        req.setAttribute("text", "다시 입력해주세요.");
        return "redirect:/mypage/delete.do";
    }
}
