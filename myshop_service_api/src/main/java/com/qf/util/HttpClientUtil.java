package com.qf.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.util.EntityUtils;

import java.io.IOException;

/**
 * @Author LWW
 * @Time Created by Enzo Cotter on 2018/11/20.
 * @Version 1.0
 */
public class HttpClientUtil {

    public static String sendjson(String url,String json){
        //构建httpclient对象

        CloseableHttpClient client = HttpClientBuilder.create().build();
        //构建post请求
        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        //设置请求体的类型格式

        StringEntity stringEntity = new StringEntity(json, "utf-8");

        //设置请求体的内容
        httpPost.setEntity(stringEntity);

        //发送post请求
        try {
            CloseableHttpResponse response = client.execute(httpPost);
            HttpEntity entity = response.getEntity();
            String result = EntityUtils.toString(entity);
            client.close();
            return result;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }
}
