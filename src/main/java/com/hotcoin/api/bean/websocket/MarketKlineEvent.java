package com.hotcoin.api.bean.websocket;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.bean.Response;

import java.util.List;

/**
 * K线数据响应
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketKlineEvent extends Response {

    /**
     * K线数据
     */
    private List<List<String>> data;

    public List<List<String>> getData() {
        return data;
    }

    public void setData(List<List<String>> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
