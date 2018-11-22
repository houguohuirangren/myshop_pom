package com.qf.myshop.myshop_service.serviceImpl;

import com.alibaba.dubbo.config.annotation.Service;
import com.qf.entity.Goods;
import com.qf.entity.PageSolr;
import com.qf.service.ISerachService;
import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

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
        SolrInputDocument solrInputFields = new SolrInputDocument();
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

    @Override
    public PageSolr<Goods> getgoodsInfoBykeyword(String keyword, PageSolr<Goods> pageSolr) {
        String querystr = null;
        //根据关键字来查询，假如不写关键字就默认查询全部
        if (keyword == null || keyword.trim().equals("")) {
            querystr = "*:*";
        } else {
            querystr = String.format("gtitle:%s || ginfo:%s", keyword, keyword);

        }

        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery(querystr);

        //开启高亮
        solrQuery.setHighlight(true);

        //设置高亮的前后缀
        solrQuery.setHighlightSimplePre("<font color='red'>");
        solrQuery.setHighlightSimplePost("</font>");

        //设置需要高亮的字段
        solrQuery.addHighlightField("gtitle");
        solrQuery.setStart((pageSolr.getCurrPage() - 1) * pageSolr.getPageSize());
        solrQuery.setRows(pageSolr.getPageSize());

        List<Goods> goodslist = new ArrayList<>();
        QueryResponse response = null;
        try {
            response = solrClient.query(solrQuery);
            SolrDocumentList results = response.getResults();
            int numFound = (int) results.getNumFound();
            pageSolr.setPageCount(numFound);
            //额外获得高亮结果
            Map<String, Map<String, List<String>>> highlighting = response.getHighlighting();

            //遍历所有的普通结果，内容没有高亮
            for (SolrDocument result : results) {
                Integer id = Integer.parseInt(result.get("id") + "");
                String title = (String) result.get("gtitle");
                float price = (float) result.get("gprice");
                Integer gcount = (Integer) result.get("gcount");
                String gimage = (String) result.get("gimage");
                //判断该商品是否存在高亮，因为根据keyword来查询的时候时根据gtitle和ginfo来查询的,
                //而在上面设定的是根据gtitle需要高亮，所以已经得到了一堆高亮的数据
                if (highlighting.containsKey(id + "")) {
                    //说明当前id的商品存在高亮内容
                    Map<String, List<String>> stringListMap = highlighting.get(id + "");
                    List<String> gtitle = stringListMap.get("gtitle");
                    if (gtitle != null) {
                        title = gtitle.get(0);
                    }
                }
                goodslist.add(new Goods(id, title, null, gcount, null, null, (double) price, gimage));


            }
            pageSolr.setDatas(goodslist);

        } catch (SolrServerException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }


        return pageSolr;
    }
}
