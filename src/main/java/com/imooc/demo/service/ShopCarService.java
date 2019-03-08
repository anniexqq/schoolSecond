package com.imooc.demo.service;

import com.imooc.demo.dto.ShopGood;
import com.imooc.demo.entity.ShopCar;

import java.util.List;

public interface ShopCarService {
    List<ShopGood> queryShopcar(ShopCar shopcar);
    boolean putInShopcar(ShopCar shopcar);
    boolean balance(String idAndNums);
}
