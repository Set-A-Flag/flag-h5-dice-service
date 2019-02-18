package com.chasel.h5.dice.util;

import org.apache.http.HttpEntity;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Http 远程调用工具类
 */
public class HttpUtils {
    //日志记录
    private static final Logger logger = LoggerFactory.getLogger(HttpUtils.class);

    /**
     * GET获取远程接口响应结果(String)
     *
     * @param remoteUrl 远程接口地址
     * @return 响应结果(String)
     */
    public static String doGetString(String remoteUrl) throws IOException {
        String resultString = null;
        CloseableHttpClient closeableHttpClient = HttpClients.createDefault();
        HttpGet httpGet = new HttpGet(remoteUrl);
        CloseableHttpResponse closeableHttpResponse = closeableHttpClient.execute(httpGet);
        HttpEntity httpEntity = closeableHttpResponse.getEntity();
        resultString = EntityUtils.toString(httpEntity,"UTF-8");
        return resultString;
    }
}
