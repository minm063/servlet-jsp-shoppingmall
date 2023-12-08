package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.cart.domain.Cart;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.domain.OrderDetail;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.List;

public interface OrderDetailService {
    void save(OrderDetail orderDetail);

    void saveOrderDetail(List<Product> productList, int orderId);

    List<List<OrderDetail>> getOrderDetailByOrderId(List<Order> orderList);
}
