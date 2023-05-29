package com.hotcoin.api.websocket;

import com.alibaba.fastjson.JSONObject;
import com.hotcoin.api.bean.websocket.*;
import com.hotcoin.api.rest.WebSocketClient;
import com.hotcoin.api.config.APIConfiguration;

/**
 * Hotcoin WebSocket 客户端实现
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class WebSocketClientImpl implements WebSocketClient {

    /**
     * 动作类型 - 订阅
     */
    public static final String ACTION_SUB = "sub";

    /**
     * 动作类型 - 取消订阅
     */
    public static final String ACTION_UNSUB = "unsub";

    /**
     * 参数 - 交易对，譬如btc_usdt
     */
    public static final String VAR_SYMBOL = "$symbol$";

    /**
     * 参数 - K线周期，@see com.hotcoin.api.constant.KlineInterval
     */
    public static final String VAR_PERIOD = "$period$";

    /**
     * WebSocket主题-实时K线
     */
    public static final String WS_KLINE_TOPIC = "market.$symbol$.kline.$period$";

    /**
     * WebSocket主题-盘口深度
     */
    public static final String WS_DEPTH_TOPIC = "market.$symbol$.trade.depth";

    /**
     * WebSocket主题-买一卖一逐笔行情
     */
    public static final String WS_BBO_TOPIC = "market.$symbol$.trade.bbo";

    /**
     * WebSocket主题-24H聚合行情
     */
    public static final String WS_TICKER_TOPIC = "market.$symbol$.24h.tickers";

    /**
     * WebSocket主题-实时成交明细
     */
    public static final String WS_TRADE_DETAIL_TOPIC = "market.$symbol$.trade.detail";


    private APIConfiguration config;

    private WebSocketConnection connection;

    public WebSocketClientImpl() {
        this.config = APIConfiguration.buildGlobal();
    }

    public WebSocketClientImpl(APIConfiguration config) {
        this.config = config;
    }

    public APIConfiguration getConfig() {
        return config;
    }

    public void setConfig(APIConfiguration config) {
        this.config = config;
    }

    public WebSocketConnection getConnection() {
        return connection;
    }

    public void setConnection(WebSocketConnection connection) {
        this.connection = connection;
    }

    @Override
    public void subMarketKline(String symbol, String period, WebSocketCallback<MarketKlineEvent> callback) {
        JSONObject command = new JSONObject();
        command.put(ACTION_SUB, WS_KLINE_TOPIC.replace(VAR_SYMBOL, symbol).replace(VAR_PERIOD, period));

        setConnection(HotcoinWebSocketConnection.createMarketConnection(config, command.toJSONString(), callback, MarketKlineEvent.class));
    }

    @Override
    public void unsubMarketKline(String symbol, String period) {
        JSONObject command = new JSONObject();
        command.put(ACTION_UNSUB, WS_KLINE_TOPIC.replace(VAR_SYMBOL, symbol).replace(VAR_PERIOD, period));

        this.getConnection().send(command.toJSONString());
    }

    @Override
    public void subMarketDepth(String symbol, WebSocketCallback callback) {
        JSONObject command = new JSONObject();
        command.put(ACTION_SUB, WS_DEPTH_TOPIC.replace(VAR_SYMBOL, symbol));

        setConnection(HotcoinWebSocketConnection.createMarketConnection(config, command.toJSONString(), callback, MarketDepthEvent.class));
    }

    @Override
    public void unsubMarketDepth(String symbol) {
        JSONObject command = new JSONObject();
        command.put(ACTION_UNSUB, WS_DEPTH_TOPIC.replace(VAR_SYMBOL, symbol));

        this.getConnection().send(command.toJSONString());
    }

    @Override
    public void subMarketBBO(String symbol, WebSocketCallback callback) {
        JSONObject command = new JSONObject();
        command.put(ACTION_SUB, WS_BBO_TOPIC.replace(VAR_SYMBOL, symbol));

        setConnection(HotcoinWebSocketConnection.createMarketConnection(config, command.toJSONString(), callback, MarketBBOEvent.class));
    }

    @Override
    public void unsubMarketBBO(String symbol) {
        JSONObject command = new JSONObject();
        command.put(ACTION_UNSUB, WS_BBO_TOPIC.replace(VAR_SYMBOL, symbol));

        this.getConnection().send(command.toJSONString());
    }

    @Override
    public void subMarketTicker(String symbol, WebSocketCallback callback) {
        JSONObject command = new JSONObject();
        command.put(ACTION_SUB, WS_TICKER_TOPIC.replace(VAR_SYMBOL, symbol));

        setConnection(HotcoinWebSocketConnection.createMarketConnection(config, command.toJSONString(), callback, MarketTickerEvent.class));
    }

    @Override
    public void unsubMarketTicker(String symbol) {
        JSONObject command = new JSONObject();
        command.put(ACTION_UNSUB, WS_TICKER_TOPIC.replace(VAR_SYMBOL, symbol));

        this.getConnection().send(command.toJSONString());
    }

    @Override
    public void subMarketTradeDetail(String symbol, WebSocketCallback callback) {
        JSONObject command = new JSONObject();
        command.put(ACTION_SUB, WS_TRADE_DETAIL_TOPIC.replace(VAR_SYMBOL, symbol));

        setConnection(HotcoinWebSocketConnection.createMarketConnection(config, command.toJSONString(), callback, MarketTradeDetailEvent.class));
    }

    @Override
    public void unsubMarketTradeDetail(String symbol) {
        JSONObject command = new JSONObject();
        command.put(ACTION_UNSUB, WS_TRADE_DETAIL_TOPIC.replace(VAR_SYMBOL, symbol));

        this.getConnection().send(command.toJSONString());
    }

    @Override
    public void closeConnection() {
        connection.close();
    }

}
