package com.imooc.demo.dao;

import com.imooc.demo.dto.ShopGood;
import com.imooc.demo.entity.ShopCar;

import java.util.List;

public interface ShopCarDao {
    List<ShopGood> queryShopCar(ShopCar shopCar);
    int insertShopCar(ShopCar shopCar);
    int updateShopCar(ShopCar shopCar);
    List<ShopCar> queryShopCarByUserAndGoodidAndIspay(ShopCar shopCar);
    ShopCar queryShopCarById(ShopCar shopCar);
    int deleteShopCarByGoodsIdAndAuthor(ShopCar shopCar);
    List<ShopCar> queryShopCarByGoodsIdAndAuthor(ShopCar shopCar);
}