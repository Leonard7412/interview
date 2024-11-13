package com.harper.template.utils;

import cn.hutool.core.util.StrUtil;
import cn.hutool.http.HttpRequest;
import cn.hutool.http.HttpResponse;

/**
 * @BelongsProject: template
 * @BelongsPackage: com.harper.template.utils
 * @Author: liuhb_mios_ah
 * @CreateTime: 2024-05-21  17:39
 * @Description: TODO
 * @Version: 1.0
 */

public class HttpUtilTool {
    /**
     * 发起GET请求
     *
     * @param url 请求URL
     * @return 响应结果
     */
    public static String get(String url) {
        if (StrUtil.isBlank(url)) {
            throw new IllegalArgumentException("URL cannot be blank.");
        }
        try {
            HttpResponse response = HttpRequest.get(url).execute();
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("GET request failed.", e);
        }
    }

    /**
     * 发起POST请求
     *
     * @param url    请求URL
     * @param params 请求参数，可为JSON字符串或Map类型
     * @return 响应结果
     */
    public static String post(String url, Object params) {
        if (StrUtil.isBlank(url)) {
            throw new IllegalArgumentException("URL cannot be blank.");
        }
        try {
            HttpResponse response;
            if (params instanceof String) {
                response = HttpRequest.post(url).body((String) params).execute();
            } else {
                throw new IllegalArgumentException("Params must be a String or a Map<String, String>.");
            }
            return response.body();
        } catch (Exception e) {
            throw new RuntimeException("POST request failed.", e);
        }
    }
}
