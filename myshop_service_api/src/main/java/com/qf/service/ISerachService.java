package com.qf.service;

import com.qf.entity.Goods;
import com.qf.entity.PageSolr;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/20.
 * @Version 1.0
 */
public interface ISerachService {

    int addSerchList(Goods goods);

    PageSolr<Goods> getgoodsInfoBykeyword(String keyword,PageSolr<Goods> pageSolr);
}
