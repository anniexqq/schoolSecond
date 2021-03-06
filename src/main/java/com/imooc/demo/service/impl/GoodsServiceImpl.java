package com.imooc.demo.service.impl;

import com.imooc.demo.dao.GoodsDao;
import com.imooc.demo.dao.MessageDao;
import com.imooc.demo.dao.ShopCarDao;
import com.imooc.demo.dto.GoodsDetailsDTO;
import com.imooc.demo.dto.MessageDTO;
import com.imooc.demo.entity.Goods;
import com.imooc.demo.entity.Message;
import com.imooc.demo.entity.ShopCar;
import com.imooc.demo.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;


import java.io.File;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class GoodsServiceImpl implements GoodsService {
    @Autowired
    private GoodsDao goodsDao;
    @Autowired
    private ShopCarDao shopCarDao;
    @Autowired
    private MessageDao messageDao;

    @Value("${imageServerURL}")
    private String imageServerURL;

    @Value("${imageUploadURL}")
    private String imageUploadURL;

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
            if(null != list && list.size()>0){
                Goods qurGoods = list.get(0);
                int effectedNum = goodsDao.deleteGoods(goods.getId());//删除商品表
                if (effectedNum <= 0) {
                    throw new RuntimeException("删除商品数据失败！");
                }
                //数据库删完成后，删除本地图片
                boolean delFlag =  deleteFile(imageUploadURL+qurGoods.getImageUrl());
                if(!delFlag) {
                    throw new RuntimeException("删除商品图片失败！");
                }
                return true;
            }
        }
        return false;
    }

    @Override
    public GoodsDetailsDTO getGoodsDetailsAndMsg(String goodsId){
        GoodsDetailsDTO goodsInfoDTO = new GoodsDetailsDTO();

        if(null == goodsId || "".equals(goodsId)){
            return null;
        }
        List<Goods> goodsList = goodsDao.queryGoodsById(goodsId);
        if(null != goodsList && goodsList.size()>0){
            Goods goods = goodsList.get(0);
            goodsInfoDTO.setGoodsId(String.valueOf(goods.getId()));
            goodsInfoDTO.setGoodsAuthorName(goods.getAuthorName());
            goodsInfoDTO.setNewPrice(goods.getNewPrice());
            goodsInfoDTO.setOldPrice(goods.getOldPrice());
            goodsInfoDTO.setGoodsName(goods.getGoodsName());
            goodsInfoDTO.setGoodsImageUrl(imageServerURL+goods.getImageUrl());//商品详情图片
            goodsInfoDTO.setGoodsDesc(goods.getGoodsDesc());
            if(null != goods.getCreateTime()) {
                String createTime = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(goods.getCreateTime());
                goodsInfoDTO.setGoodsCreateTime(createTime);
            }
        }else{
            return null;
        }

        List<Message> msgList = messageDao.queryMessageByGoodsId(goodsId);
        List<MessageDTO> messageDTOList = new ArrayList<>();
        for(Message message:msgList){
            MessageDTO messageDTO = new MessageDTO();
            messageDTO.setMessageId(String.valueOf(message.getId()));
            messageDTO.setUserImage(message.getUserImage());
            messageDTO.setUserName(message.getUserName());
            messageDTO.setComment(message.getComment());
            messageDTO.setReplayUserName(message.getReplyUserName());
            messageDTOList.add(messageDTO);
        }
        goodsInfoDTO.setMessageList(messageDTOList);
        goodsInfoDTO.setMsgCount(String.valueOf(messageDTOList.size()));
        return goodsInfoDTO;
    }

    /**
     * 删除单个文件
     *
     * @param fileName 要删除的文件的文件名
     * @return 单个文件删除成功返回true，否则返回false
     */
    public static boolean deleteFile(String fileName) {
        File file = new File(fileName);
        // 如果文件路径所对应的文件存在，并且是一个文件，则直接删除
        if (file.exists() && file.isFile()) {
            if (file.delete()) {
                return true;
            } else {
                return false;
            }
        } else {
            System.out.println("删除单个文件失败：" + fileName + "不存在！");
            return false;
        }
    }
}
