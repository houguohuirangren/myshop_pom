package com.qf.dao;

import com.qf.entity.Goods;

import java.util.List;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/19.
 * @Version 1.0
 */
public interface IGoodsDao {
    List<Goods> getAllGoods();


    void addGoods(Goods goods);

    List<Goods> getnewGoodsList();
}
