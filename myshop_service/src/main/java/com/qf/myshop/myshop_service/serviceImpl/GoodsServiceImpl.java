package com.qf.myshop.myshop_service.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.dao.IGoodsDao;
import com.qf.entity.Goods;
import com.qf.service.IGoodsService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/19.
 * @Version 1.0
 */
@Service
public class GoodsServiceImpl implements IGoodsService {
    @Autowired
    IGoodsDao iGoodsDao;

    @Override
    public List<Goods> getAllGoods() {
        return iGoodsDao.getAllGoods();
    }

    @Override
    public Goods addGoods(Goods goods) {
        iGoodsDao.addGoods(goods);
        return goods;
    }

    @Override
    public List<Goods> getnewGoodsList() {
        return iGoodsDao.getnewGoodsList();
    }


}
