package com.imooc.demo.entity;

public class Address {
    private Integer id;
    private String consignee;
    private String mobile;
    private String transportDay;
    private String address;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getConsignee() {
        return consignee;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getTransportDay() {
        return transportDay;
    }

    public void setTransportDay(String transportDay) {
        this.transportDay = transportDay;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}