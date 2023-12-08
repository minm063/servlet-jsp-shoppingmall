package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.common.page.Page;
import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;
import java.util.Optional;

public interface OrderRepository {

    int save(Order order);

    int findId();

    Optional<Order> findOrder(int orderId);

    Page<Order> findOrderOnPageByUserId(String userId, int page, int pageSize);

    int totalCountByUserId(String userId);
}
