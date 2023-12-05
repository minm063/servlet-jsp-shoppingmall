package com.nhnacademy.shoppingmall.controller.mypage.admin.user;

import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.user.domain.User;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.util.Objects;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/admin/user.do")
public class UserController implements BaseController {

    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    private static final int PAGE_SIZE = 10;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) {
        long page = (Objects.isNull(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page")));
        long start = (page - 1) * PAGE_SIZE;
        System.out.println(page);
        System.out.println(start);
        Page<User> users = userService.getUserByPage(User.Auth.ROLE_USER, Math.toIntExact(page), PAGE_SIZE);

        System.out.println(users);
        long total = users.getTotalCount();
        total = (total == 0) ? 1 : total;
        total = (total % PAGE_SIZE == 0) ? total / PAGE_SIZE : (total / PAGE_SIZE) + 1;

        long currentBlock = page % PAGE_SIZE == 0 ? page / PAGE_SIZE : (page / PAGE_SIZE) + 1;
        long startPage = (currentBlock - 1) * PAGE_SIZE + 1;
        long endPage = startPage + PAGE_SIZE - 1;
        if (total < endPage) {
            endPage = total;
        }
        req.setAttribute("users", users);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("totalPage", total);
        return "shop/user/mypage/manage/user";
    }
}
