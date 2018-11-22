package com.qf.myshop.myshop_serach.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.entity.PageSolr;
import com.qf.service.ISerachService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/20.
 * @Version 1.0
 */
@Controller
@RequestMapping("/search")
public class SearchController {
    @Reference
    private ISerachService serachService;
    @Value("${image.path}")
    String path;

    @RequestMapping("/addsearchList")
    @ResponseBody
    public String addsearchList(@RequestBody Goods goods){
        int result = serachService.addSerchList(goods);
        if (result==1){
            return "success";
        }else {
            return "error";
        }
    }


    @RequestMapping("/getgoodsInfo")
    public String getgoodsInfo(String keyword, PageSolr<Goods> pageSolr, Model model){
        //这里要有返回值，因为这是跨工程进行对象传递，对象地址变了
        pageSolr=serachService.getgoodsInfoBykeyword(keyword,pageSolr);
        model.addAttribute("pageSolr", pageSolr);
        model.addAttribute("path", path);
        model.addAttribute("keyword",keyword );


        return "searchGoodsList";
    }


}
