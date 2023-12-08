package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;

public interface OrderService {

    void save(Order order);

    int getId();

    Order getOrder();

    Page<Order> getOrderOnPageByUserId(String userId, int page, int pageSize);

    int getTotalCountByUserId(String userId);
}
