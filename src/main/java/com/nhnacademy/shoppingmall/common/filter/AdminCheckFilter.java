package com.nhnacademy.shoppingmall.common.filter;

import com.nhnacademy.shoppingmall.user.domain.User;
import java.io.IOException;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@WebFilter("/admin/*")
public class AdminCheckFilter extends HttpFilter {
    @Override
    protected void doFilter(HttpServletRequest req, HttpServletResponse res, FilterChain chain)
            throws IOException, ServletException {
        //todo#11 /admin/ 하위 요청은 관리자 권한의 사용자만 접근할 수 있습니다. ROLE_USER가 접근하면 403 Forbidden 에러처리
        if (req.getSession().getAttribute("role") != User.Auth.ROLE_ADMIN) {
            res.sendError(HttpServletResponse.SC_FORBIDDEN);
            return;
        }
        chain.doFilter(req, res);
    }
}
