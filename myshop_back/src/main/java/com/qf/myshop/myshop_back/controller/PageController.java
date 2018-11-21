package com.qf.myshop.myshop_back.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/19.
 * @Version 1.0
 */
@Controller
public class PageController {
    @RequestMapping("/topage/{page}")
    public String topageByname(@PathVariable("page") String page){
        return page;
    }
}
