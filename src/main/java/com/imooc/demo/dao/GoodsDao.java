package com.imooc.demo.dao;

import com.imooc.demo.entity.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> queryGoods();
    List<Goods> queryGoodsByAuthor(String author);
    List<Goods> queryGoodsByTitle(String goodsName);
    int insertGoods(Goods goods);
}