package com.gt.instruct.common.util;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;

import java.io.IOException;

/**
 * @author linweicong
 * @version 2018-01-18 10:38:15
 */
public class HttpUtil {
    private static int DEFAULT_TIME_OUT = 5000;

    public static int httpGet(String url) {
        RequestConfig requestConfig = RequestConfig.custom()
                .setSocketTimeout(DEFAULT_TIME_OUT)
                .setConnectionRequestTimeout(DEFAULT_TIME_OUT)
                .setConnectionRequestTimeout(DEFAULT_TIME_OUT)
                .setStaleConnectionCheckEnabled(true)
                .build();
        CloseableHttpClient httpClient = HttpClients.custom()
                .setDefaultRequestConfig(requestConfig)
                .build();
        HttpGet httpGet = new HttpGet(url);
        httpGet.setConfig(requestConfig);
        CloseableHttpResponse httpResponse = null;
        try {
            httpResponse = httpClient.execute(httpGet);
            return httpResponse.getStatusLine().getStatusCode();
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        } finally {
            if (httpResponse != null) {
                try {
                    httpResponse.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

}
