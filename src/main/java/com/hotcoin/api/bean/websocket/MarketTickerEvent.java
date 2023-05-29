package com.hotcoin.api.bean.websocket;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.bean.Response;

/**
 * 24H聚合数据响应
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketTickerEvent extends Response {

    /**
     * 24H聚合数据
     */
    private MarketTicker data;

    public MarketTicker getData() {
        return data;
    }

    public void setData(MarketTicker data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
