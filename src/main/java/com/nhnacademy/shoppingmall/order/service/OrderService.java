package com.nhnacademy.shoppingmall.order.service;

import com.nhnacademy.shoppingmall.order.domain.Order;

public interface OrderService {

    void save(Order order);

    int getId();

    Order getOrder();
}
