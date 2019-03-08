package com.imooc.demo.web;

import com.imooc.demo.entity.Address;
import com.imooc.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 收货地址管理
 */
@RestController
@RequestMapping("/address")
public class AddressController {
    @Autowired
    private AddressService addressService;

    @RequestMapping(value="/listAddress",method = RequestMethod.GET)
    private Map<String,Object> listAddress(){
        System.out.println("----------------查询地址信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Address> list = addressService.queryAddress();
        modelMap.put("addressList",list);
        return modelMap;
    }

    @RequestMapping(value="/addAddress",method = RequestMethod.POST)
    private Map<String,Object> addAddress(@RequestBody Address address){
        System.out.println("----------------新增地址信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        boolean flag = addressService.addAddress(address);
        modelMap.put("success",flag);
        return modelMap;
    }

    @RequestMapping(value="/removeAddress",method = RequestMethod.GET)
    private Map<String,Object> removeAddress(Integer addressId){
        System.out.println("----------------删除地址信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        modelMap.put("success",addressService.deleteAddress(addressId));
        return modelMap;
    }
}
