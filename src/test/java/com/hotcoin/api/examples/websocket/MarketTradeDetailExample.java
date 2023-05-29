package com.hotcoin.api.examples.websocket;

import com.hotcoin.api.rest.WebSocketClient;

/**
 * 实时成交 Example
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketTradeDetailExample {

    public static void main(String[] args) {

        WebSocketClient webSocketClient = WebSocketClient.create();

        String symbol = "eth_usdt";

        webSocketClient.subMarketTradeDetail(symbol, (tradeDetail) -> {
            System.out.println("tradeDetail==" + tradeDetail.toString());
        });
    }

}
