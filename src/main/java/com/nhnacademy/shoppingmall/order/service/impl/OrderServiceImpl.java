package com.nhnacademy.shoppingmall.order.service.impl;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;
import com.nhnacademy.shoppingmall.order.repository.OrderRepository;
import com.nhnacademy.shoppingmall.order.service.OrderService;
import java.util.ArrayList;
import java.util.Optional;

public class OrderServiceImpl implements OrderService {

    private final OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public void save(Order order) {
        orderRepository.save(order);
    }

    @Override
    public int getId() {
        return orderRepository.findId();
    }

    @Override
    public Order getOrder() {
        int orderId = orderRepository.findId();
        Optional<Order> orderOptional = orderRepository.findOrder(orderId);
        if (orderOptional.isPresent()) {
            return orderOptional.get();
        }
        return null;
    }

    @Override
    public Page<Order> getOrderOnPageByUserId(String userId, int page, int pageSize) {
        if (orderRepository.totalCountByUserId(userId) == 0) {
            return new Page<>(new ArrayList<>(), 0);
        }
        return orderRepository.findOrderOnPageByUserId(userId, page, pageSize);
    }

    @Override
    public int getTotalCountByUserId(String userId) {
        return orderRepository.totalCountByUserId(userId);
    }
}
