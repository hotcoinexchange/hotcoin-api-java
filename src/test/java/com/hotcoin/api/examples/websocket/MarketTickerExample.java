package com.hotcoin.api.examples.websocket;

import com.hotcoin.api.rest.WebSocketClient;

/**
 * 24H聚合行情 Example
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketTickerExample {

    public static void main(String[] args) {

        WebSocketClient webSocketClient = WebSocketClient.create();

        String symbol = "eth_usdt";

        webSocketClient.subMarketTicker(symbol, (ticker) -> {
            System.out.println("ticker==" + ticker.toString());
        });
    }

}
