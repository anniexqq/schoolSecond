package com.imooc.demo.web;

import com.imooc.demo.entity.Message;
import com.imooc.demo.service.MessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.*;

/**
 * 留言管理
 */
@RestController
@RequestMapping("/message")
public class MessageController {
    @Autowired
    private MessageService messageService;

    @RequestMapping(value="/addMessage",method = RequestMethod.POST)
    private Map<String,Object> addMessage(@RequestBody Message message){
        System.out.println("----------------新增留言------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        message.setCreateTime(new Date());
        int newMessageId = messageService.addMessage(message);
        modelMap.put("messageId",newMessageId);
        return modelMap;
    }

}
