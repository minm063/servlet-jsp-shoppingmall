package com.nhnacademy.shoppingmall.controller.order;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import java.util.Objects;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/orderAction.do")
public class OrderPostController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 포인트 차감, 장바구니에서 지우기, 재고........
        // 주문이 완료되면 주문 금액의 10%를 point로 적립받습니다.
        // 단일 주문이 들어가 있는 주문, 주문 상세
        HttpSession httpSession = req.getSession(false);
        String userId = String.valueOf(httpSession.getAttribute("userId"));



        return "redirect:/index.do";
    }
}
