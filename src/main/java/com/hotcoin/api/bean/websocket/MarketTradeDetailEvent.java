package com.hotcoin.api.bean.websocket;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.bean.Response;

import java.util.List;

/**
 * 实时成交明细响应
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketTradeDetailEvent extends Response {

    /**
     * 实时成交明细数据
     */
    private List<MarketTradeDetail> data;

    public List<MarketTradeDetail> getData() {
        return data;
    }

    public void setData(List<MarketTradeDetail> data) {
        this.data = data;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
