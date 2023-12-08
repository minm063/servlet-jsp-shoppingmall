package com.nhnacademy.shoppingmall.order.service.impl;

import com.nhnacademy.shoppingmall.order.repository.impl.OrderRepositoryImpl;

public class OrderServiceImpl {

    private final OrderRepositoryImpl orderRepository;

    public OrderServiceImpl(OrderRepositoryImpl orderRepository) {
        this.orderRepository = orderRepository;
    }
}
