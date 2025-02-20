package com.nhnacademy.shoppingmall.controller.cart;


import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.domain.User;
import java.io.IOException;
import java.time.LocalDateTime;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/cartAction.do")
public class CartPostController implements BaseController {

    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    CartService cartService = new CartServiceImpl(new CartRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);

        if (httpSession.getAttribute("role").equals(User.Auth.ROLE_ADMIN)) {
            return "redirect:/index.do";
        }

        String userId = String.valueOf(httpSession.getAttribute("userId"));
        int productId = Integer.parseInt(req.getParameter("productId"));
        String quantity = req.getParameter("quantity");
        if (quantity.isEmpty() || !quantity.matches("^[0-9]+")) {
            return "redirect:/index/product.do?id=" + productId;
        }

        Cart cart = new Cart(
                Integer.parseInt(req.getParameter("quantity")),
                LocalDateTime.now(),
                productId,
                userId
        );
        cartService.save(cart);
        return "redirect:/cart.do";
    }
}
