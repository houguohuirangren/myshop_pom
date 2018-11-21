package com.qf.myshop.myshop_serach;

import org.apache.solr.client.solrj.SolrClient;
import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServerException;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.apache.solr.common.SolrDocument;
import org.apache.solr.common.SolrDocumentList;
import org.apache.solr.common.SolrInputDocument;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;

@RunWith(SpringRunner.class)
@SpringBootTest
public class MyshopSerachApplicationTests {

    @Autowired
    SolrClient solrClient;

    @Test
    public void contextLoads() throws IOException, SolrServerException {

        for (int i = 2; i < 20; i++) {
            SolrInputDocument solrInputFields = new SolrInputDocument();
            solrInputFields.addField("id", i);
            solrInputFields.addField("gtitle", "小米手机" + i);
            solrInputFields.addField("ginfo", "小米手机详情" + i);
            solrInputFields.addField("gprice", 199.0 + i);
            solrInputFields.addField("gcount", 10 + i);
            solrInputFields.addField("gimage", "http://www.baidu.com");
            solrClient.add(solrInputFields);
        }
        solrClient.commit();
    }


    @Test
    public void getList() throws IOException, SolrServerException {
        SolrQuery solrQuery = new SolrQuery();
        solrQuery.setQuery("gtitle:小米");
        QueryResponse query = solrClient.query(solrQuery);
        SolrDocumentList results = query.getResults();
        for (SolrDocument result : results) {
            String id = (String) result.get("id");
            String gtitle = (String) result.get("gtitle");
            float gprice = (float) result.get("gprice");
            String ginfo = (String) result.get("ginfo");
            int gcount = (int) result.get("gcount");
            String gimage = (String) result.get("gimage");
            System.out.println(id+" "+gcount+" "+gtitle+" "+ginfo+" "+gprice+" "+gimage);

        }

    }

}
