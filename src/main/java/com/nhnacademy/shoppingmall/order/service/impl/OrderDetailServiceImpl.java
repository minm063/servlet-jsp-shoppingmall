package com.nhnacademy.shoppingmall.order.service.impl;

import com.nhnacademy.shoppingmall.order.repository.impl.OrderDetailRepositoryImpl;

public class OrderDetailServiceImpl {

    private final OrderDetailRepositoryImpl orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepositoryImpl orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }
}
