package com.nhnacademy.shoppingmall.order.repository;

import com.nhnacademy.shoppingmall.order.domain.OrderDetail;
import java.util.Optional;

public interface OrderDetailRepository {

    int save(OrderDetail orderDetail);

    Optional<OrderDetail> findOrderDetailByProductId(int productId, int orderId);
}
