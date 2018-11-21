package com.qf.myshop.myshop_service;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.qf.dao")
@DubboComponentScan("com.qf.myshop.myshop_service.serviceImpl")
public class MyshopServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(MyshopServiceApplication.class, args);
    }
}
