package com.nhnacademy.shoppingmall.controller.user.order;


import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.repository.impl.AddressRepositoryImpl;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.address.service.impl.AddressServiceImpl;
import com.nhnacademy.shoppingmall.common.mvc.annotation.RequestMapping;
import com.nhnacademy.shoppingmall.common.mvc.controller.BaseController;
import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.domain.OrderDetail;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderDetailRepositoryImpl;
import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;
import com.nhnacademy.shoppingmall.order.service.OrderDetailService;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import com.nhnacademy.shoppingmall.order.service.impl.OrderDetailServiceImpl;
import com.nhnacademy.shoppingmall.order.service.impl.OrderServiceImpl;
import com.nhnacademy.shoppingmall.product.domain.Category;
import com.nhnacademy.shoppingmall.product.domain.Product;
import com.nhnacademy.shoppingmall.product.repository.impl.ProductRepositoryImpl;
import com.nhnacademy.shoppingmall.product.service.ProductService;
import com.nhnacademy.shoppingmall.product.service.impl.ProductServiceImpl;
import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.stream.Stream;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RequestMapping(method = RequestMapping.Method.GET, value = "/mypage/ordering.do")
public class OrderingController implements BaseController {

    private final OrderService orderService = new OrderServiceImpl(new OrderRepositoryImpl());
    private final AddressService addressService = new AddressServiceImpl(new AddressRepositoryImpl());
    private final OrderDetailService orderDetailService = new OrderDetailServiceImpl(new OrderDetailRepositoryImpl());
    private final ProductService productService = new ProductServiceImpl(new ProductRepositoryImpl());
    private final int PAGE_SIZE = 3;

    @Override
    public String execute(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        HttpSession httpSession = req.getSession(false);

        if (Objects.isNull(httpSession.getAttribute("userId"))) {
            log.info("user id is null @@@");
            return "redirect:/index.do";
        }

        String userId = String.valueOf(httpSession.getAttribute("userId"));
        int page = (Objects.isNull(req.getParameter("page")) ? 1 : Integer.parseInt(req.getParameter("page")));

        Page<Order> orderPage = orderService.getOrderOnPageByUserId(userId, page, PAGE_SIZE);
        for (Order order : orderPage.getContent()) {
            log.info("order: {}", order.getOrderId());
        }
        List<Address> addressList = addressService.getAddressList(orderPage.getContent());
        for (Address address : addressList) {
            log.info("address: {}", address.getAddressId());
        }

        List<List<OrderDetail>> orderDetailList = orderDetailService.getOrderDetailByOrderId(orderPage.getContent());
        for (List<OrderDetail> orderDetailList1 : orderDetailList) {
            for (OrderDetail orderDetail : orderDetailList1) {
                log.info("order: {}", orderDetail.getOrderId());
            }
            log.info("<hr /> order");
        }

        List<List<Product>> productList = productService.getProductsByProductId(orderDetailList);


        int total = orderService.getTotalCountByUserId(userId);
        total = (total == 0) ? 1 : total;
        total = (total % PAGE_SIZE == 0) ? total / PAGE_SIZE : (total / PAGE_SIZE) + 1;

        int currentBlock = page / PAGE_SIZE + 1;
        int startPage = (currentBlock - 1) * PAGE_SIZE + 1;
        int endPage = total < PAGE_SIZE ? total : startPage + PAGE_SIZE - 1;

        req.setAttribute("orderList", orderPage);
        req.setAttribute("addressList", addressList);
        req.setAttribute("orderDetailList", orderDetailList);
        req.setAttribute("productList", productList);
        req.setAttribute("startPage", startPage);
        req.setAttribute("endPage", endPage);
        req.setAttribute("page", page);

        return "shop/mypage/user/ordering";
    }
}
