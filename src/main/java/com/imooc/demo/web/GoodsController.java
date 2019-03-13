package com.imooc.demo.web;

import com.imooc.demo.entity.Goods;
import com.imooc.demo.service.GoodsService;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.omg.PortableInterceptor.SYSTEM_EXCEPTION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
//import org.apache.commons.lang3.StringUtils;

/**
 * 商品管理
 */
@RestController
@RequestMapping("/goods")
public class GoodsController {
    @Autowired
    private GoodsService goodsService;

    @RequestMapping(value="/listGoods",method = RequestMethod.GET)
    private Map<String,Object> listGoods(){
        System.out.println("----------------查询商品信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Goods> list = goodsService.queryGoods();
        modelMap.put("goodsList",list);
        return modelMap;
    }

    @RequestMapping(value="/getGoodsByAuthor",method = RequestMethod.GET)
    private Map<String,Object> getGoodsByAuthor(String author){
        System.out.println("----------------根据用户查询商品信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Goods> list = goodsService.queryGoodsByAuthor(author);
        modelMap.put("authorGoodsList",list);
        return modelMap;
    }
    @RequestMapping(value="/getGoodsByTitle",method = RequestMethod.GET)
    private Map<String,Object> getGoodsByTitle(String goodsName){
        System.out.println("----------------搜索商品------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        List<Goods> list = goodsService.queryGoodsByTitle(goodsName);
        modelMap.put("goodsList",list);
        return modelMap;
    }

    @RequestMapping(value="/addGoods",method = RequestMethod.POST)
    private Map<String,Object> addGoods(@RequestBody Goods goods){
        System.out.println("----------------新增商品信息------------------");
        Map<String,Object> modelMap = new HashMap<String,Object>();
        goods.setImageUrl("/images/addtu.jpg");//先使用一个默认的图片，后面图片上传成功后再更新
        int newGoodsId = goodsService.addGoods(goods);
        modelMap.put("goodsId",newGoodsId);
        return modelMap;
    }

    @PostMapping(value="/uploadGoodsImg" ,headers="content-type=multipart/form-data")
    public Map<String,Object> uploadGoodsImg(int goodsId,@RequestParam("file") MultipartFile[] files) throws Exception {
        Map<String,Object> modelMap = new HashMap<String,Object>();
        //文件保存的命名空间
        String fileSpace="E:/IdeaProjects/schoolSecond/small";
        //保存到数据库的相对路径
        String uploadPathDB="/images";
        FileOutputStream fileOutputStream=null;
        InputStream inputStream= null;
        try {
            if(files != null && files.length>0) {
                String fileName = files[0].getOriginalFilename();
                String suffix = fileName.substring(fileName.lastIndexOf(".") + 1);
                fileName = System.currentTimeMillis() + String.valueOf((int)(Math.random()*100))+"."+suffix;
                System.out.println("----imgName:"+fileName);
                if (null != fileName && !fileName.equals("")) {
                    //文件保存的最终路径
                    String finalPath = fileSpace + uploadPathDB + "/" + fileName;
                    //设置数据库保存的路径
                    uploadPathDB += ("/" + fileName);
                    File outFile = new File(finalPath);
                    if (outFile.getParentFile() != null || !outFile.getParentFile().isDirectory()) {
                        //创建父文件夹
                        outFile.getParentFile().mkdirs();
                    }
                    fileOutputStream = new FileOutputStream(outFile);
                    inputStream = files[0].getInputStream();
                    IOUtils.copy(inputStream, fileOutputStream);
                } else {
                    modelMap.put("status", "500");
                    modelMap.put("msg", "上传出错");
                    return modelMap;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            modelMap.put("status","500");
            modelMap.put("msg","上传出错");
            return modelMap;
        }finally {
            if(fileOutputStream!=null) {
                fileOutputStream.flush();
                fileOutputStream.close();
            }
        }
        Goods goods = new Goods();
        goods.setId(goodsId);
        goods.setImageUrl(uploadPathDB);
        boolean update = goodsService.updateGoodsImg(goods);
        if(update){
            modelMap.put("status","200");
            modelMap.put("msg","上传成功");
        }else{
            modelMap.put("status","500");
            modelMap.put("msg","上传出错");
        }
        return modelMap;
    }

}
