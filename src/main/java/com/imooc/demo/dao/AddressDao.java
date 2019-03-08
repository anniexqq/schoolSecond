package com.imooc.demo.dao;

import com.imooc.demo.entity.Address;

import java.util.List;

public interface AddressDao {
    List<Address> queryAddress();
    int insertAddress(Address address);
    int deleteAddress(int id);
}