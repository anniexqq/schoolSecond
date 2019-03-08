package com.imooc.demo.web;

import com.imooc.demo.dto.ShopGood;
import com.imooc.demo.entity.ShopCar;
import com.imooc.demo.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 购物车管理
 */
@RestController
@RequestMapping("/shopcar")
public class ShopCarController {
    @Autowired
    private ShopCarService shopcarService;

    //查询我的购物车；
    //查询用户已购商品
    @RequestMapping(value="/listUserShopcar",method = RequestMethod.POST)
    private Map<String,Object> listUserShopcar(@RequestBody ShopCar shopcar){
        //根据用户名、isPay查询购物车商品
        System.out.println("----------------查询用户：【 "+shopcar.getUserName()+" 】 的购物车------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<ShopGood> list = shopcarService.queryShopcar(shopcar);
        modelMap.put("goodsList",list);
        return modelMap;
    }

    //加入购物车
    @RequestMapping(value="/putInShopcar",method = RequestMethod.POST)
    private Map<String,Object> putInShopcar(@RequestBody ShopCar shopcar){
        //商品id，用户名，isPay
        Map<String,Object> modelMap = new HashMap<String,Object>();
        boolean flag = shopcarService.putInShopcar(shopcar);
        modelMap.put("success",flag);
        return modelMap;
    }

    //结算
    @RequestMapping(value="/balance",method = RequestMethod.GET)
    private Map<String,Object> balance(String idAndNums){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        boolean flag = shopcarService.balance(idAndNums);
        modelMap.put("success",flag);
        return modelMap;
    }

}
