package com.hotcoin.api.constant;

import okhttp3.MediaType;

import java.nio.charset.Charset;

/**
 * Hotcoin ApiKey Config
 * Hotcoin ApiKey配置
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public interface ApiConstants {

    String LANG_PARAM = "lang";

    String CONTENT_TYPE = "Content-Type";

    String HTTP_POST = "POST";

    String HTTP_GET = "GET";

    String JSON_UTF8 = "application/json; charset=UTF-8";

    String FORM_UTF8 = "application/x-www-form-urlencoded; charset=UTF-8";

    MediaType JSON_UTF8_MT = MediaType.parse(JSON_UTF8);

    String UTF8 = "UTF-8";

    Charset UTF_8 = Charset.forName(UTF8);

    /**
     * signed host
     * 用于签名的固定不变host
     */
    String SIGN_HOST = "api.hotcoinfin.com";

    String PING = "{\"ping\":";

    String PONG = "{\"pong\":\"pong\"}";

    String CHAR_AND = "&";

    String CHAR_EQUAL = "=";

    String CHAR_QST = "?";

    String EMPTY = "";

    String MIDDLE_LINE = "-";

    String LINE_BREAK = "\n";

    String CHAR_PLUS = "\\+";

    String TRANS_PLUS = "%20";

}
