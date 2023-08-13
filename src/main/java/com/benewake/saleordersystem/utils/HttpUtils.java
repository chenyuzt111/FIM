package com.benewake.saleordersystem.utils;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.utils.URIBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * @author xsong
 * @date 2022/5/7 10:16
 */
public class HttpUtils {
    // 获得http客户端（相当于一个浏览器）
    static CloseableHttpClient httpClient = HttpClientBuilder.create().build();

    public static HttpResponse doGet(String scheme, String host, String path, Map<String, String> bodys, Map<String, String> headers) throws Exception {
        //拼接请求地址
        URI uri = null;
        URIBuilder uriBuilder = new URIBuilder();
        List<NameValuePair> list = new ArrayList<>();
        //拼接请求体
        if (null != bodys) {
            for (Map.Entry<String, String> body : bodys.entrySet()) {
                list.add(new BasicNameValuePair(body.getKey(), body.getValue()));
            }
            uriBuilder.setParameters(list);
        }
        uri = uriBuilder.setScheme(scheme).setHost(host).setPath(path).build();
        System.out.println("uri" + uri);
        //使用get请求
        HttpGet httpGet = new HttpGet(uri);
        //拼接请求头
        if (null != headers) {
            for (Map.Entry<String, String> header : headers.entrySet()) {
                httpGet.addHeader(header.getKey(), header.getValue());
            }
        }
        return httpClient.execute(httpGet);
    }
}
