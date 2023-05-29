package com.hotcoin.api.examples.websocket;

import com.hotcoin.api.rest.WebSocketClient;

/**
 * 盘口深度 Example
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketDepthExample {

    public static void main(String[] args) {

        WebSocketClient webSocketClient = WebSocketClient.create();

        String symbol = "btc_usdt";

        webSocketClient.subMarketDepth(symbol, (depth) -> {
            System.out.println("depth==" + depth.toString());
        });
    }

}
