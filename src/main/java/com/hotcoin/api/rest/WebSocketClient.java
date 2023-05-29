package com.hotcoin.api.rest;

import com.hotcoin.api.bean.websocket.MarketKlineEvent;
import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.websocket.WebSocketCallback;
import com.hotcoin.api.websocket.WebSocketClientImpl;

/**
 * Hotcoin WebSocket 客户端接口
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public interface WebSocketClient {

    /**
     * 订阅实时K线
     *
     * @param symbol   交易对，譬如btc_usdt
     * @param period   K线周期，@see com.hotcoin.api.constant.KlineInterval
     * @param callback
     */
    void subMarketKline(String symbol, String period, WebSocketCallback<MarketKlineEvent> callback);

    /**
     * 取消订阅实时K线
     *
     * @param symbol 交易对，譬如btc_usdt
     * @param period K线周期，@see com.hotcoin.api.constant.KlineInterval
     */
    void unsubMarketKline(String symbol, String period);

    /**
     * 订阅盘口深度
     *
     * @param symbol   交易对，譬如btc_usdt
     * @param callback
     */
    void subMarketDepth(String symbol, WebSocketCallback callback);

    /**
     * 取消订阅盘口深度
     *
     * @param symbol 交易对，譬如btc_usdt
     */
    void unsubMarketDepth(String symbol);

    /**
     * 订阅买一卖一逐笔行情
     *
     * @param symbol   交易对，譬如btc_usdt
     * @param callback
     */
    void subMarketBBO(String symbol, WebSocketCallback callback);

    /**
     * 取消订阅买一卖一逐笔行情
     *
     * @param symbol 交易对，譬如btc_usdt
     */
    void unsubMarketBBO(String symbol);

    /**
     * 订阅24H聚合行情
     *
     * @param symbol   交易对，譬如btc_usdt
     * @param callback
     */
    void subMarketTicker(String symbol, WebSocketCallback callback);

    /**
     * 取消订阅24H聚合行情
     *
     * @param symbol 交易对，譬如btc_usdt
     */
    void unsubMarketTicker(String symbol);

    /**
     * 订阅实时成交明细
     *
     * @param symbol   交易对，譬如btc_usdt
     * @param callback
     */
    void subMarketTradeDetail(String symbol, WebSocketCallback callback);

    /**
     * 取消订阅实时成交明细
     *
     * @param symbol 交易对，譬如btc_usdt
     */
    void unsubMarketTradeDetail(String symbol);

    /**
     * 关闭连接
     */
    void closeConnection();

    /**
     * 创建WebSocket客户端
     *
     * @return
     */
    static WebSocketClient create() {
        return new WebSocketClientImpl();
    }

    /**
     * 创建WebSocket客户端
     *
     * @param config
     * @return
     */
    static WebSocketClient create(APIConfiguration config) {
        return new WebSocketClientImpl(config);
    }

}
