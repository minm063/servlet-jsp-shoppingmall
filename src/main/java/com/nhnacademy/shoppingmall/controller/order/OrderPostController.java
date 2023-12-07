package com.nhnacademy.shoppingmall.controller.order;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.POST, value = "/orderAction.do")
public class OrderPostController implements BaseController {
    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        // 포인트 차감
        // 단일 주문이 들어가 있는 주문, 주문 상세

        return "redirect:/index.do";
    }
}
