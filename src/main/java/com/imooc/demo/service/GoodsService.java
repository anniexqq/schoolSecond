package com.imooc.demo.service;

import com.imooc.demo.entity.Goods;

import java.util.List;

public interface GoodsService {
    List<Goods> queryGoods();
    List<Goods> queryGoodsByAuthor(String author);
    List<Goods> queryGoodsByTitle(String title);
    boolean addGoods(Goods goods);
}
