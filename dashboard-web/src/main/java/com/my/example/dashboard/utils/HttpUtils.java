package com.my.example.dashboard.utils;

import okhttp3.*;
import org.apache.commons.collections.MapUtils;

import java.io.IOException;
import java.util.Map;

/**
 * Created by user on 16/7/20.
 */
public class HttpUtils {
    private final static String APPLICATION_JSON_UTF8_VALUE = "application/json;charset=UTF-8";
    private static final OkHttpClient httpClient = new OkHttpClient.Builder().build();


    public static String get(String url, Map<String, Object> params) {
        try {
            Request request = new Request.Builder().url(buildGetUrl(url, params)).build();
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private static String buildGetUrl(String url, Map<String, Object> params) {
        String urlString = url;
        if (MapUtils.isNotEmpty(params)) {
            StringBuilder sb = new StringBuilder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                if (entry.getValue() != null) {
                    sb.append(entry.getKey()).append("=").append(String.valueOf(entry.getValue())).append("&");
                }
            }
            if (sb.length() > 0) {
                urlString = urlString + "?" + sb.substring(0, sb.length() - 1);
            }
        }
        return urlString;
    }


    public static String postJson(String url, String jsonContent) {
        try {
            RequestBody requestBody = RequestBody.create(MediaType.parse(APPLICATION_JSON_UTF8_VALUE), jsonContent);
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static String postForm(String url, Map<String, Object> params) {
        try {
            FormBody.Builder builder = new FormBody.Builder();
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                builder.add(entry.getKey(), entry.getValue() != null ? String.valueOf(entry.getValue()) : null);
            }
            RequestBody formBody = builder.build();
            Request request = new Request.Builder()
                    .url(url)
                    .post(formBody)
                    .build();
            Response response = httpClient.newCall(request).execute();
            if (response.isSuccessful()) {
                return response.body().string();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
