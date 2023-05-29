package com.hotcoin.api.utils;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.constant.ApiConstants;
import okhttp3.Request;
import org.apache.commons.lang3.StringUtils;

/**
 * Hotcoin Api Rest Connection
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class HotcoinRestConnection {

    /**
     * API request configuration
     * API请求配置
     */
    private APIConfiguration config;

    public HotcoinRestConnection(APIConfiguration config) {
        this.config = config;
    }

    /**
     * Publish Rest Api For Get HttpMethod
     * 公共接口 - 无需认证
     *
     * @param uri
     * @param urlBuilder
     * @return
     */
    public String executeGet(String uri, UrlBuilder urlBuilder) {
        String urlParams = urlBuilder.buildUrl();
        String fullUrl;
        if (StringUtils.isBlank(urlParams)) {
            fullUrl = config.getRestEndpoint() + uri;
        } else {
            fullUrl = config.getRestEndpoint() + uri + ApiConstants.CHAR_QST + urlBuilder.buildUrl();
        }
        Request executeRequest = new Request.Builder()
                .url(fullUrl)
                .addHeader(ApiConstants.CONTENT_TYPE, ApiConstants.FORM_UTF8)
                .addHeader(ApiConstants.LANG_PARAM, config.getI18n().getName())
                .build();

        return ConnectionFactory.execute(executeRequest);
    }

    /**
     * Signed Rest Api For Get HttpMethod
     * 私有接口 - 签名验证
     *
     * @param uri
     * @param urlBuilder
     * @return
     */
    public String executeSignedGet(String uri, UrlBuilder urlBuilder) {
        String fullUrl = config.getRestEndpoint() + uri;
        SignatureGenerator.createSignature(config, ApiConstants.HTTP_GET, uri, urlBuilder);
        fullUrl = fullUrl + ApiConstants.CHAR_QST + urlBuilder.buildUrl();
        Request executeRequest = new Request.Builder().url(fullUrl)
                .addHeader(ApiConstants.CONTENT_TYPE, ApiConstants.FORM_UTF8)
                .addHeader(ApiConstants.LANG_PARAM, config.getI18n().getName())
                .build();

        return ConnectionFactory.execute(executeRequest);
    }

    /**
     * Signed Rest Api For Post HttpMethod
     * 私有接口 - 签名验证
     *
     * @param uri
     * @param urlBuilder
     * @return
     */
    public String executeSignedPost(String uri, UrlBuilder urlBuilder) {
        String fullUrl = config.getRestEndpoint() + uri;
        SignatureGenerator.createSignature(config, ApiConstants.HTTP_POST, uri, urlBuilder);
        fullUrl = fullUrl + ApiConstants.CHAR_QST + urlBuilder.buildUrl();
        Request executeRequest = new Request.Builder().url(fullUrl).post(urlBuilder.buildPostBody())
                .addHeader(ApiConstants.CONTENT_TYPE, ApiConstants.JSON_UTF8)
                .addHeader(ApiConstants.LANG_PARAM, config.getI18n().getName())
                .build();

        return ConnectionFactory.execute(executeRequest);
    }

}
