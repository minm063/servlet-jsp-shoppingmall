package com.nhnacademy.shoppingmall.address.repository;

import com.nhnacademy.shoppingmall.address.domain.Address;
import java.util.List;
import java.util.Optional;

public interface AddressRepository {

    int save(Address address);

    int update(int addressId, String address);

    int delete(int addressId);

    List<Address> findAddressListByUserId(String userId);

    Optional<Address> findAddressByAddressId(int addressId);

    int countByUserId(String userId);

    int countByAddressId(int addressId);
}
