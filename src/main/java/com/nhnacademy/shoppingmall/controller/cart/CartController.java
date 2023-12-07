package com.nhnacademy.shoppingmall.controller.cart;


import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequestMapping(method = RequestMapping.Method.GET, value = "/cart.do")
public class CartController implements BaseController {

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        return "";
    }
}
