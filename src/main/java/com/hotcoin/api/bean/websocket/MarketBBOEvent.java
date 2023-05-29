package com.hotcoin.api.bean.websocket;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.bean.Response;

/**
 * 买一卖一逐笔行情响应
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketBBOEvent extends Response {

    /**
     * 买一卖一逐笔行情
     */
    private MarketBBO data;

    public MarketBBO getData() {
        return data;
    }

    public void setData(MarketBBO data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
