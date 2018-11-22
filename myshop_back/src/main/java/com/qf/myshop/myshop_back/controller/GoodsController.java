package com.qf.myshop.myshop_back.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import com.google.gson.Gson;
import com.qf.entity.Goods;
import com.qf.entity.PageSolr;
import com.qf.service.IGoodsService;
import com.qf.util.HttpClientUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/19.
 * @Version 1.0
 */
@Controller
@RequestMapping("/goods")
public class GoodsController {
    @Reference
    IGoodsService goodsService;

    @Value("${image.path}")
    String spath;

    @Autowired
    public FastFileStorageClient fastFileStorageClient;

    @RequestMapping("/getAllGoodsInfo")
    public String getAllGoods(Model model, PageSolr<Goods> pageSolr){
        List<Goods> goods = goodsService.getAllGoods();
        model.addAttribute("goods",goods );
        model.addAttribute("spath", spath);
        return "goodsInfo";
    }

    @RequestMapping("/addGoods")
    public String addGoods( MultipartFile gfile,Goods goods) throws IOException {
        StorePath result = fastFileStorageClient.uploadImageAndCrtThumbImage(gfile.getInputStream(), gfile.getSize(), "jpg", null);
        String path=result.getFullPath();
        goods.setGimage(path);
        Goods jsonGoods = goodsService.addGoods(goods);
        HttpClientUtil.sendjson("http://localhost:8282/search/addsearchList", new Gson().toJson(jsonGoods));
        HttpClientUtil.sendjson("http://localhost:8383/item/createhtml", new Gson().toJson(jsonGoods));

        return "redirect:/goods/getAllGoodsInfo";
    }

    @RequestMapping("/getnewGoodsList")
    @ResponseBody
    @CrossOrigin
    public List<Goods>  getnewGoodsList() {
        List<Goods> goods=goodsService.getnewGoodsList();
        return goods;
    }



}
