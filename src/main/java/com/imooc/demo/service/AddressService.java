package com.imooc.demo.service;

import com.imooc.demo.entity.Address;
import java.util.List;

public interface AddressService {
    List<Address> queryAddress();
    boolean addAddress(Address address);
    boolean deleteAddress(int id);
}
