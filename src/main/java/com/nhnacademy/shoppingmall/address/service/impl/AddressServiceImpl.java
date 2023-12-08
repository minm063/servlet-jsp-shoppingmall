package com.nhnacademy.shoppingmall.address.service.impl;

import com.nhnacademy.shoppingmall.address.domain.Address;
import com.nhnacademy.shoppingmall.address.exception.AddressNotFoundException;
import com.nhnacademy.shoppingmall.address.repository.AddressRepository;
import com.nhnacademy.shoppingmall.address.service.AddressService;
import com.nhnacademy.shoppingmall.order.domain.Order;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class AddressServiceImpl implements AddressService {

    AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public void save(Address address) {
        addressRepository.save(address);
    }

    @Override
    public void update(int addressId, String address) {
        if (addressRepository.countByAddressId(addressId) == 0) {
            throw new AddressNotFoundException(String.valueOf(addressId));
        }
        addressRepository.update(addressId, address);
    }

    @Override
    public void delete(int addressId) {
        if (addressRepository.countByAddressId(addressId) == 0) {
            throw new AddressNotFoundException(String.valueOf(addressId));
        }
        addressRepository.delete(addressId);
    }

    @Override
    public List<Address> getAddressList(String userId) {
        if (addressRepository.countByUserId(userId) == 0) {
            return new ArrayList<>();
        }

        return addressRepository.findAddressListByUserId(userId);
    }

    @Override
    public Address getAddress(int addressId) {
        Optional<Address> address = addressRepository.findAddressByAddressId(addressId);
        if (address.isPresent()) {
            return address.get();
        }
        return null;
    }

    @Override
    public List<Address> getAddressList(List<Order> orderList) {
        List<Address> addressList = new ArrayList<>();
        for (Order order : orderList) {
            if (addressRepository.findAddressByAddressId(order.getAddressId()).isPresent()) {
                addressList.add(addressRepository.findAddressByAddressId(order.getAddressId()).get());
            }
        }
        return addressList;
    }
}
