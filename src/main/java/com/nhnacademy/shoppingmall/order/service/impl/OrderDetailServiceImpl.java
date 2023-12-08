package com.nhnacademy.shoppingmall.order.service.impl;

import com.nhnacademy.shoppingmall.order.domain.OrderDetail;
import com.nhnacademy.shoppingmall.order.repository.OrderDetailRepository;
import com.nhnacademy.shoppingmall.order.service.OrderDetailService;
import com.nhnacademy.shoppingmall.product.domain.Product;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderDetailServiceImpl implements OrderDetailService {

    private final OrderDetailRepository orderDetailRepository;

    public OrderDetailServiceImpl(OrderDetailRepository orderDetailRepository) {
        this.orderDetailRepository = orderDetailRepository;
    }

    @Override
    public void save(OrderDetail orderDetail) {
        orderDetailRepository.save(orderDetail);
    }

    @Override
    public void saveOrderDetail(List<Product> productList, int orderId) {
        for (Product product : productList) {
            Optional<OrderDetail> orderDetailOptional =
                    orderDetailRepository.findOrderDetailByProductId(product.getProductId(), orderId);

            if (orderDetailOptional.isPresent()) {
                orderDetailRepository.save(orderDetailOptional.get());
            }
        }
    }
}
