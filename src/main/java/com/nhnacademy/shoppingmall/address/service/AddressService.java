package com.nhnacademy.shoppingmall.address.service;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.List;

public interface AddressService {

    void save(Address address);

    void update(int addressId, String address);

    void delete(int addressId);
    List<Address> getAddressList(String userId);

    Address getAddress(int addressId);

    List<Address> getAddressList(List<Order> orderList);
}
