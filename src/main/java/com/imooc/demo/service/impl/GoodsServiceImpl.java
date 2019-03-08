package com.imooc.demo.service.impl;

import com.imooc.demo.dao.GoodsDao;
import com.imooc.demo.entity.Goods;
import com.imooc.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;

    @Override
    public List<Goods> queryGoods() {
        return goodsDao.queryGoods();
    }

    @Override
    public List<Goods> queryGoodsByAuthor(String author) {
        return goodsDao.queryGoodsByAuthor(author);
    }

    @Override
    public List<Goods> queryGoodsByTitle(String title) {
        return goodsDao.queryGoodsByTitle(title);
    }

    @Transactional
    @Override
    public boolean addGoods(Goods goods) {
        if(goods.getGoodsName() != null && !"".equals(goods.getGoodsName())
                && goods.getNewPrice() != null && !"".equals(goods.getNewPrice())
                && goods.getOldPrice() != null && !"".equals(goods.getOldPrice())){
            try{
                int effectedNum = goodsDao.insertGoods(goods);
                if(effectedNum>0){
                    return true;
                }else{
                    //当抛出RuntimeException异常时，事务就会回滚
                    throw new RuntimeException("新增商品信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("新增商品信息失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("收货人和手机号不能为空！");
        }
    }
}
