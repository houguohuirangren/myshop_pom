package com.qf.myshop.myshop_serach.controller;

import com.alibaba.dubbo.config.annotation.Reference;
import com.qf.entity.Goods;
import com.qf.service.ISerachService;
import org.springframework.stereotype.Controller;
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

}
