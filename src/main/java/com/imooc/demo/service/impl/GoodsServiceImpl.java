package com.imooc.demo.service.impl;

import com.imooc.demo.dao.GoodsDao;
import com.imooc.demo.dao.ShopCarDao;
import com.imooc.demo.dto.GoodsInfoDTO;
import com.imooc.demo.entity.Goods;
import com.imooc.demo.entity.ShopCar;
import com.imooc.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ShopCarDao shopCarDao;

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
    public int addGoods(Goods goods) {
        if(goods.getGoodsName() != null && !"".equals(goods.getGoodsName())
                      && goods.getNewPrice() != null && !"".equals(goods.getNewPrice())
                 && goods.getOldPrice() != null && !"".equals(goods.getOldPrice())){
            try{
                int effectedNum = goodsDao.insertGoods(goods);
                if(effectedNum>0){
                    return goods.getId();
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

    @Transactional
    @Override
    public boolean updateGoodsImg(Goods goods) {
        try {
            int effectedNum = goodsDao.updateGoods(goods);
            if (effectedNum > 0) {
                return true;
            } else {
                //当抛出RuntimeException异常时，事务就会回滚
                throw new RuntimeException("更新图片失败！");
            }
        } catch (Exception e) {
            throw new RuntimeException("更新图片失败:" + e.getMessage());
        }
    }
    @Transactional
    @Override
    public boolean deleteMyGoods(Goods goods){
        //购物车（已支付、未支付）表数据（goodsId、userName）、删除商品表（id）
        if(null != goods && null!=goods.getId() && null!=goods.getAuthorName()){
            ShopCar delShopCar = new ShopCar();
            delShopCar.setGoodsId(goods.getId());
            delShopCar.setUserName(goods.getAuthorName());
            int effNum = shopCarDao.deleteShopCarByGoodsIdAndAuthor(delShopCar);

            List<Goods> list = goodsDao.queryGoodsById(String.valueOf(goods.getId()));
            if(null != list){
                int effectedNum = goodsDao.deleteGoods(goods.getId());//删除商品表
                if (effectedNum <= 0) {
                    throw new RuntimeException("删除商品数据失败！");
                }
            }
            return true;
        }
        return false;
    }

    @Override
    public Map<String,Object> getGoodsDetailsAndMsg(String goodsId){
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<GoodsInfoDTO> newList = new ArrayList<>();
        GoodsInfoDTO goodsInfoDTO = new GoodsInfoDTO();
        goodsInfoDTO.setUserImage("/images/tu1.jpg");
        goodsInfoDTO.setUserName("我是哈士奇");
        newList.add(goodsInfoDTO);
        modelMap.put("goodsInfoList",newList);
        modelMap.put("msgCount","0");
        return modelMap;
    }
}
