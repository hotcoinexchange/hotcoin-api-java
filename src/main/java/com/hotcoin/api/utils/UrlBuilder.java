package com.hotcoin.api.utils;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.constant.ApiConstants;
import com.hotcoin.api.exceptions.HotcoinApiException;
import okhttp3.RequestBody;
import org.apache.commons.lang3.StringUtils;

import java.io.UnsupportedEncodingException;
import java.math.BigDecimal;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Hotcoin Api UrlBuilder
 * Hotcoin Api 请求参数处理工具类
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class UrlBuilder {

    class ParamsMap {

        /**
         * 普通参数Map<String, String>
         */
        final Map<String, String> pmap = new HashMap<>();

        /**
         * 多个List参数Map<String, List<T>>
         */
        final Map<String, List> listMap = new HashMap<>();

        /**
         * 单个List参数List<T>
         */
        final List jsonlist = new ArrayList();

        void put(String name, String value) {
            if (StringUtils.isBlank(name)) {
                throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR, "Url Key can not be null");
            }
            if (StringUtils.isBlank(value)) {
                return;
            }

            pmap.put(name, value);
        }

        void put(String name, Integer value) {
            put(name, value != null ? Integer.toString(value) : null);
        }

        void put(String name, Date value, String format) {
            SimpleDateFormat dateFormatter = new SimpleDateFormat(format);
            put(name, value != null ? dateFormatter.format(value) : null);
        }

        void put(String name, Long value) {
            put(name, value != null ? Long.toString(value) : null);
        }

        void put(String name, BigDecimal value) {
            put(name, value != null ? value.toPlainString() : null);
        }

    }

    /**
     * url参数
     */
    private final ParamsMap ulrParamMap = new ParamsMap();

    /**
     * application/json参数
     */
    private final ParamsMap postBodyMap = new ParamsMap();

    public static UrlBuilder build() {
        return new UrlBuilder();
    }

    private UrlBuilder() {
    }

    /**
     * 写入url参数-Enum
     *
     * @param name
     * @param value
     * @param <T>
     * @return
     */
    public <T extends Enum> UrlBuilder putToUrl(String name, T value) {
        if (value != null) {
            ulrParamMap.put(name, value.toString());
        }
        return this;
    }

    /**
     * 写入url参数-String
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToUrl(String name, String value) {
        ulrParamMap.put(name, value);
        return this;
    }

    /**
     * 写入url参数-Date
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToUrl(String name, Date value, String format) {
        ulrParamMap.put(name, value, format);
        return this;
    }

    /**
     * 写入url参数-Integer
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToUrl(String name, Integer value) {
        ulrParamMap.put(name, value);
        return this;
    }

    /**
     * 写入url参数-Long
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToUrl(String name, Long value) {
        ulrParamMap.put(name, value);
        return this;
    }

    /**
     * 写入url参数-BigDecimal
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToUrl(String name, BigDecimal value) {
        ulrParamMap.put(name, value);
        return this;
    }


    /**
     * 写入post请求体-String
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToPost(String name, String value) {
        postBodyMap.put(name, value);
        return this;
    }

    /**
     * 写入post请求体-Enum
     *
     * @param name
     * @param value
     * @return
     */
    public <T extends Enum> UrlBuilder putToPost(String name, T value) {
        if (value != null) {
            postBodyMap.put(name, value.toString());
        }
        return this;
    }

    /**
     * 写入post请求体-Date
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToPost(String name, Date value, String format) {
        postBodyMap.put(name, value, format);
        return this;
    }

    /**
     * 写入post请求体-Integer
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToPost(String name, Integer value) {
        postBodyMap.put(name, value);
        return this;
    }

    /**
     * 写入post请求体-List
     *
     * @param name
     * @param list
     * @return
     */
    public <T> UrlBuilder putToPost(String name, List<T> list) {
        postBodyMap.listMap.put(name, list);
        return this;
    }

    /**
     * 写入post请求体-List
     * 仅适用于接口请求体是List<T>结构
     *
     * @param list
     * @return
     */
    public <T> UrlBuilder putToPost(List<T> list) {
        postBodyMap.jsonlist.addAll(list);
        return this;
    }

    /**
     * 写入post请求体-Long
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToPost(String name, Long value) {
        postBodyMap.put(name, value);
        return this;
    }

    /**
     * 写入post请求体-BigDecimal
     *
     * @param name
     * @param value
     * @return
     */
    public UrlBuilder putToPost(String name, BigDecimal value) {
        postBodyMap.put(name, value);
        return this;
    }

    /**
     * 拼接url参数
     *
     * @return
     */
    public String buildUrl() {
        Map<String, String> urlMap = new HashMap<>(ulrParamMap.pmap);
        StringBuilder stringBuilder = new StringBuilder();
        return appendUrl(urlMap, stringBuilder);
    }

    /**
     * 创建签名串
     *
     * @return
     */
    public String buildSignature() {
        Map<String, String> urlMap = new TreeMap<>(ulrParamMap.pmap);
        StringBuilder sb = new StringBuilder();
        return appendUrl(urlMap, sb);
    }

    /**
     * 拼接url参数
     *
     * @param urlMap
     * @param stringBuilder
     * @return
     */
    private String appendUrl(Map<String, String> urlMap, StringBuilder stringBuilder) {
        for (Map.Entry<String, String> entry : urlMap.entrySet()) {
            if (StringUtils.isNotBlank(stringBuilder.toString())) {
                stringBuilder.append(ApiConstants.CHAR_AND);
            }
            stringBuilder.append(entry.getKey());
            stringBuilder.append(ApiConstants.CHAR_EQUAL);
            stringBuilder.append(urlEncode(entry.getValue()));
        }
        return stringBuilder.toString();
    }

    /**
     * 构建Post请求体
     *
     * @return
     */
    public RequestBody buildPostBody() {
        if (postBodyMap.listMap.isEmpty()) {
            if (postBodyMap.jsonlist.isEmpty()) {
                if (postBodyMap.pmap.isEmpty()) {
                    return RequestBody.create(ApiConstants.EMPTY, ApiConstants.JSON_UTF8_MT);
                } else {
                    // Post + JSON(Map)
                    return RequestBody.create(JSON.toJSONString(postBodyMap.pmap), ApiConstants.JSON_UTF8_MT);
                }
            } else {
                // Post + JSON(单个List)
                return RequestBody.create(JSON.toJSONString(postBodyMap.jsonlist), ApiConstants.JSON_UTF8_MT);
            }
        } else {
            // Post + JSON(多个List)
            return RequestBody.create(JSON.toJSONString(postBodyMap.listMap), ApiConstants.JSON_UTF8_MT);
        }
    }

    /**
     * url参数转JSON字符串
     *
     * @return
     */
    public String buildUrlToJsonString() {
        return JSON.toJSONString(ulrParamMap.pmap);
    }

    /**
     * 使用标准URL Encode编码
     *
     * @param str 待编码字符串
     * @return URL编码后的字符串
     */
    private static String urlEncode(String str) {
        try {
            return URLEncoder.encode(str, ApiConstants.UTF8).replaceAll(ApiConstants.CHAR_PLUS, ApiConstants.TRANS_PLUS);
        } catch (UnsupportedEncodingException ex) {
            throw new HotcoinApiException(HotcoinApiException.RUNTIME_ERROR, "UTF-8 encoding not supported");
        }
    }

}
