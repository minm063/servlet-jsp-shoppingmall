package com.nhnacademy.shoppingmall.address.service;

import com.nhnacademy.shoppingmall.address.domain.Address;
import java.util.List;

public interface AddressService {

    void save(Address address);

    void update(int addressId, String address);

    void delete(int addressId);
    List<Address> getAddressList(String userId);

    Address getAddress(int addressId);
}
