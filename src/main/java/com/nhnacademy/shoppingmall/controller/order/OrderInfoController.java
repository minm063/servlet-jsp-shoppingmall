package com.nhnacademy.shoppingmall.controller.order;


import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.POST, value = "/order.do")
public class OrderInfoController implements BaseController {

    private final CartService cartService = new CartServiceImpl(new CartRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());
    private final UserService userService = new UserServiceImpl(new UserRepositoryImpl());

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        HttpSession httpSession = req.getSession(false);

        if (Objects.isNull(httpSession) || Objects.isNull(httpSession.getAttribute("userId"))) {
            return "redirect:/index.do";
        }
        String userId = String.valueOf(httpSession.getAttribute("userId"));
        int userPoint = userService.getUser(userId).getUserPoint();
        List<Cart> cartList = cartService.getCartByUserId(userId);
        List<Product> productList = productService.getProducts(cartList);
        List<Address> addressList = addressService.getAddressList(userId);
        if (Objects.nonNull(req.getParameterValues("quantities"))) {
            String[] quantitiesArray = req.getParameterValues("quantities");
            List<Integer> quantities = Arrays.stream(quantitiesArray)
                    .map(Integer::parseInt)
                    .collect(Collectors.toList());

            cartService.updateQuantitiesOnProduct(cartList, quantities);
        }
        req.setAttribute("cartList", cartService.getCartByUserId(userId));
        req.setAttribute("productList", productList);
        req.setAttribute("addressList", addressList);
        req.setAttribute("userPoint", userPoint);

        return "shop/order/order_info";
    }
}
