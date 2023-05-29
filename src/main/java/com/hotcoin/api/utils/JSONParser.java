package com.hotcoin.api.utils;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.exceptions.HotcoinApiException;
import org.apache.commons.lang3.StringUtils;

/**
 * JSON解析器
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class JSONParser<T> {

    public static <T> T parseObject(String data, Class<T> clazz) {
        if (StringUtils.isBlank(data)) {
            throw new HotcoinApiException(HotcoinApiException.RESULT_ERROR, "Response Error From Server");
        }
        return JSON.parseObject(data, clazz);
    }

}
