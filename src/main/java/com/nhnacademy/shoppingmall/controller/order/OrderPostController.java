package com.nhnacademy.shoppingmall.controller.order;


import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.cart.repository.impl.CartRepositoryImpl;
import com.nhnacademy.shoppingmall.cart.service.CartService;
import com.nhnacademy.shoppingmall.cart.service.impl.CartServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.domain.OrderDetail;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderDetailService;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderDetailServiceImpl;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.point.domain.Point;
import com.nhnacademy.shoppingmall.point.repository.impl.PointRepositoryImpl;
import com.nhnacademy.shoppingmall.point.service.PointService;
import com.nhnacademy.shoppingmall.point.service.impl.PointServiceImpl;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import com.nhnacademy.shoppingmall.thread.channel.RequestChannel;
import com.nhnacademy.shoppingmall.thread.request.impl.PointChannelRequest;
import com.nhnacademy.shoppingmall.user.repository.impl.UserRepositoryImpl;
import com.nhnacademy.shoppingmall.user.service.UserService;
import com.nhnacademy.shoppingmall.user.service.impl.UserServiceImpl;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.Deque;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@RequestMapping(method = RequestMapping.Method.POST, value = "/orderAction.do")
public class OrderPostController implements BaseController {

    OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    CartService cartService = new CartServiceImpl(new CartRepositoryImpl());
    ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    UserService userService = new UserServiceImpl(new UserRepositoryImpl());
    OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());
    PointService pointService = new PointServiceImpl(new PointRepositoryImpl());


    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        // 포인트 차감, 장바구니에서 지우기, 재고........
        // 주문이 완료되면 주문 금액의 10%를 point로 적립받습니다.
        // 단일 주문이 들어가 있는 주문, 주문 상세
        HttpSession httpSession = req.getSession(false);
        String userId = String.valueOf(httpSession.getAttribute("userId"));
        int addressId = Integer.parseInt(req.getParameter("addressRadio"));
        int totalSum = Integer.parseInt(req.getParameter("totalSum"));
        // delete from cart where user_id=? ok
        // point decrement -> userId, now, totalSum ok
        // product stock decrement -> productId ok
        // user point decrement -> update user_point ok

        // order) users(userId), address Id ok
        // order detail) product, cart, order ok

        List<Cart> cartList = cartService.getCartByUserId(userId);
        List<Product> productList = productService.getProducts(cartList);

        ServletContext servletContext = req.getServletContext();
        RequestChannel requestChannel = (RequestChannel) servletContext.getAttribute("requestChannel");
        try {
            requestChannel.addRequest(new PointChannelRequest(userId, LocalDateTime.now(), (int) (totalSum * 0.1)));
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }

        userService.updatePoint(userId, -1 * totalSum);
        pointService.save(new Point(-1 * totalSum, LocalDateTime.now(), userId));
        orderService.save(new Order(LocalDateTime.now(), LocalDateTime.now().plusDays(3), userId, addressId));
        Order order = orderService.getOrder();

        orderDetailService.saveOrderDetail(productList, order.getOrderId());

        // productList 주고 service에서 update 돌려야됨
        productService.updateProductStock(productList);
        cartService.deleteCartByUserId(userId);

        return "redirect:/index.do";
    }
}
