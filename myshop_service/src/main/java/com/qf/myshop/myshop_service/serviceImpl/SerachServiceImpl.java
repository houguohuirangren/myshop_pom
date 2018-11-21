package com.qf.myshop.myshop_service.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.service.ISerachService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/20.
 * @Version 1.0
 */
@Service
public class SerachServiceImpl implements ISerachService {
    @Autowired
    SolrClient solrClient;
    @Override
    public int addSerchList(Goods goods) {
        SolrInputDocument solrInputFields=new SolrInputDocument();
        solrInputFields.addField("id", goods.getId());
        solrInputFields.addField("gtitle", goods.getTitle());
        solrInputFields.addField("ginfo", goods.getGinfo());
        solrInputFields.addField("gcount", goods.getGcount());
        solrInputFields.addField("gprice", goods.getPrice());
        solrInputFields.addField("gimage", goods.getGimage());
        try {
            solrClient.add(solrInputFields);
            solrClient.commit();
            return 1;
        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return 0;
    }
}
