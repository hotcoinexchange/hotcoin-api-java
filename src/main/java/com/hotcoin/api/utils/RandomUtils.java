package com.hotcoin.api.utils;

import com.hotcoin.api.constant.ApiConstants;

import java.util.UUID;

/**
 * 随机字符工具类
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class RandomUtils {

    /**
     * 生成32位UUID
     *
     * @return 32位UUID
     */
    public static String get32UUID() {
        return UUID.randomUUID().toString().trim().replaceAll(ApiConstants.MIDDLE_LINE, ApiConstants.EMPTY);
    }

}
