package com.hotcoin.api.bean.websocket;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.bean.Response;

/**
 * 盘口深度响应
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketDepthEvent extends Response {

    /**
     * 盘口深度数据
     */
    private MarketDepth data;

    public MarketDepth getData() {
        return data;
    }

    public void setData(MarketDepth data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
