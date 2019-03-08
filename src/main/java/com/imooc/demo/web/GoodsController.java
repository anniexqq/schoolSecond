package com.imooc.demo.web;

import com.imooc.demo.entity.Goods;
import com.imooc.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 商品管理
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value="/listGoods",method = RequestMethod.GET)
    private Map<String,Object> listGoods(){
        System.out.println("----------------查询商品信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Goods> list = goodsService.queryGoods();
        modelMap.put("goodsList",list);
        return modelMap;
    }

    @RequestMapping(value="/getGoodsByAuthor",method = RequestMethod.GET)
    private Map<String,Object> getGoodsByAuthor(String author){
        System.out.println("----------------根据用户查询商品信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Goods> list = goodsService.queryGoodsByAuthor(author);
        modelMap.put("authorGoodsList",list);
        return modelMap;
    }
    @RequestMapping(value="/getGoodsByTitle",method = RequestMethod.GET)
    private Map<String,Object> getGoodsByTitle(String goodsName){
        System.out.println("----------------搜索商品------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Goods> list = goodsService.queryGoodsByTitle(goodsName);
        modelMap.put("goodsList",list);
        return modelMap;
    }

    @RequestMapping(value="/addGoods",method = RequestMethod.POST)
    private Map<String,Object> addGoods(@RequestBody Goods goods){
        System.out.println("----------------新增商品信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        goods.setImageUrl("/images/addtu.jpg");
        boolean flag = goodsService.addGoods(goods);
        modelMap.put("success",flag);
        return modelMap;
    }
}
