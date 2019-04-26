package com.imooc.demo.service.impl;

import com.imooc.demo.dao.MessageDao;
import com.imooc.demo.entity.Message;
import com.imooc.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class MessageServiceImpl implements MessageService {
    @Autowired
    private MessageDao messageDao;

    @Transactional
    @Override
    public int addMessage(Message message) {
        if(message.getGoodsId() != null && !"".equals(message.getGoodsId())
                && message.getComment() != null && !"".equals(message.getComment())
                && message.getCreateTime()!= null && !"".equals(message.getCreateTime())
                && message.getUserName() !=null && !"".equals(message.getUserName())
                && message.getUserImage() !=null && !"".equals(message.getUserImage())
                ){
            try{
                int effectedNum = messageDao.insertMessage(message);
                if(effectedNum>0){
                    return message.getId();
                }else{
                    //当抛出RuntimeException异常时，事务就会回滚
                    throw new RuntimeException("新增留言失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("新增留言失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("不能为空！");
        }
    }

}
