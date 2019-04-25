package com.imooc.demo.dao;

import com.imooc.demo.entity.Message;

import java.util.List;

public interface MessageDao {
    //根据商品ID查存留言
    List<Message> queryMessageByGoodsId();
}