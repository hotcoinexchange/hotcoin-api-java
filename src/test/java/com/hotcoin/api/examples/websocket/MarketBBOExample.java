package com.hotcoin.api.examples.websocket;

import com.hotcoin.api.rest.WebSocketClient;

/**
 * 买一卖一逐笔行情 Example
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketBBOExample {

    public static void main(String[] args) {

        WebSocketClient webSocketClient = WebSocketClient.create();

        String symbol = "eth_usdt";

        webSocketClient.subMarketBBO(symbol, (depth) -> {
            System.out.println("depth==" + depth.toString());
        });
    }

}
