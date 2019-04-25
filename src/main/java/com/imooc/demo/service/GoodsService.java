package com.imooc.demo.service;

import com.imooc.demo.entity.Goods;

import java.util.List;
import java.util.Map;

public interface GoodsService {
    List<Goods> queryGoods();
    List<Goods> queryGoodsByAuthor(String author);
    List<Goods> queryGoodsByTitle(String title);
    int addGoods(Goods goods);
    boolean updateGoodsImg(Goods goods);

    public boolean deleteMyGoods(Goods goods);
    public Map<String,Object> getGoodsDetailsAndMsg(String goodsId);
}
