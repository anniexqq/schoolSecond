package com.imooc.demo.dao;

import com.imooc.demo.entity.Goods;

import java.util.List;

public interface GoodsDao {
    List<Goods> queryGoods();
    List<Goods> queryGoodsByAuthor(String author);
    List<Goods> queryGoodsByTitle(String goodsName);
    List<Goods> queryGoodsById(String id);
    int insertGoods(Goods goods);
    int updateGoods(Goods goods);
    int deleteGoods(int id);
}