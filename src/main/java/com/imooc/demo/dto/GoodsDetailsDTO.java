package com.imooc.demo.dto;

import java.util.List;

public class GoodsDetailsDTO {
    private String goodsId;
    private String goodsName;
    private String goodsDesc;
    private String newPrice;
    private String oldPrice;
    private String goodsImageUrl;
    private String goodsAuthorName;
    private String goodsCreateTime;//商品发布时间

    private String msgCount;//总留言条数
    private List<MessageDTO> messageList;

    public String getMsgCount() {
        return msgCount;
    }

    public void setMsgCount(String msgCount) {
        this.msgCount = msgCount;
    }



    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsDesc() {
        return goodsDesc;
    }

    public void setGoodsDesc(String goodsDesc) {
        this.goodsDesc = goodsDesc;
    }

    public String getNewPrice() {
        return newPrice;
    }

    public void setNewPrice(String newPrice) {
        this.newPrice = newPrice;
    }

    public String getOldPrice() {
        return oldPrice;
    }

    public void setOldPrice(String oldPrice) {
        this.oldPrice = oldPrice;
    }

    public String getGoodsImageUrl() {
        return goodsImageUrl;
    }

    public void setGoodsImageUrl(String goodsImageUrl) {
        this.goodsImageUrl = goodsImageUrl;
    }

    public String getGoodsAuthorName() {
        return goodsAuthorName;
    }

    public void setGoodsAuthorName(String goodsAuthorName) {
        this.goodsAuthorName = goodsAuthorName;
    }

    public String getGoodsCreateTime() {
        return goodsCreateTime;
    }

    public void setGoodsCreateTime(String goodsCreateTime) {
        this.goodsCreateTime = goodsCreateTime;
    }

    public List<MessageDTO> getMessageList() {
        return messageList;
    }

    public void setMessageList(List<MessageDTO> messageList) {
        this.messageList = messageList;
    }
}