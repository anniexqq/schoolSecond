package com.imooc.demo.service.impl;

import com.imooc.demo.dao.AddressDao;
import com.imooc.demo.entity.Address;
import com.imooc.demo.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    private AddressDao addressDao;

    @Override
    public List<Address> queryAddress() {
        return addressDao.queryAddress();
    }


    @Transactional
    @Override
    public boolean addAddress(Address address) {
        if(address.getConsignee() != null && !"".equals(address.getConsignee())
                && address.getMobile() != null && !"".equals(address.getMobile())){//收货人和手机号不能为空
            try{
                int effectedNum = addressDao.insertAddress(address);
                if(effectedNum>0){
                    return true;
                }else{
                    //当抛出RuntimeException异常时，事务就会回滚
                    throw new RuntimeException("新增地址信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("新增地址信息失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("收货人和手机号不能为空！");
        }
    }

    @Override
    public boolean deleteAddress(int id) {
        if(id>0){
            try{
                int effectedNum = addressDao.deleteAddress(id);
                if(effectedNum>0){
                    return true;
                }else{
                    //当抛出RuntimeException异常时，事务就会回滚
                    throw new RuntimeException("删除地址信息失败！");
                }
            }catch (Exception e){
                throw new RuntimeException("删除地址信息失败:"+e.getMessage());
            }
        }else{
            throw new RuntimeException("地址ID不能为空！");
        }
    }
}
