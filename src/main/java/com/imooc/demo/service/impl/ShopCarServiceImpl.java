package com.imooc.demo.service.impl;

import com.imooc.demo.dao.ShopCarDao;
import com.imooc.demo.dto.ShopGood;
import com.imooc.demo.entity.ShopCar;
import com.imooc.demo.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Service
public class ShopCarServiceImpl implements ShopCarService{
    @Autowired
    private ShopCarDao shopCarDao;

    public List<ShopGood> queryShopcar(ShopCar shopcar){
        return shopCarDao.queryShopCar(shopcar);
    }

    //加入购物车
    public boolean putInShopcar(ShopCar shopcar){
        //如果之前购物车中没有未支付的该商品，则create，如果有则数量加1
        shopcar.setIsPay("0");
        List<ShopCar> shopCars = shopCarDao.queryShopCarByUserAndGoodidAndIspay(shopcar);
        if (null!=shopCars && shopCars.size()>0){
            shopcar = shopCars.get(0);
            int count = Integer.parseInt(shopcar.getGoodsNumber());
            count++;
            shopcar.setGoodsNumber(String.valueOf(count));
            int effNum = shopCarDao.updateShopCar(shopcar);
            if (effNum>0){
                return true;
            }else{
                throw new RuntimeException("购物车数量加1失败！");
            }
        }else{
            shopcar.setGoodsNumber("1");
            int effectedNum = shopCarDao.insertShopCar(shopcar);
            if (effectedNum>0){
                return true;
            }else{
                throw new RuntimeException("加入购物车失败！");
            }
        }
    }

    public boolean balance(String idAndNums){
        if(null == idAndNums) return false;
        String[] arr = idAndNums.split(";");//购物车表id，数量；
        for(int i=0;i<arr.length;i++) {
            String[] garr = arr[i].split(",");
            int id = Integer.parseInt(garr[0]);
            String num = garr[1];
            ShopCar shopCar = new ShopCar();
            shopCar.setId(id);
            shopCar.setIsPay("1");
            shopCar.setGoodsNumber(num);
            Date date = new Date();
            String payTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
            shopCar.setPayTime(payTime);
            shopCarDao.updateShopCar(shopCar);
        }
            return true;
    }
    public List<ShopCar> queryShopCarByGoodsIdAndAuthor(int goodsId,String userName){
        ShopCar shopCar = new ShopCar();
        shopCar.setGoodsId(goodsId);
        shopCar.setUserName(userName);
        List<ShopCar> list = shopCarDao.queryShopCarByGoodsIdAndAuthor(shopCar);
        return list;
    }
}
