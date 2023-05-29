package com.hotcoin.api.examples.websocket;

import com.hotcoin.api.constant.KlineInterval;
import com.hotcoin.api.rest.WebSocketClient;

/**
 * 实时K线 Example
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketKlineExample {

    public static void main(String[] args) {

        WebSocketClient webSocketClient = WebSocketClient.create();

        String symbol = "eth_usdt";
        String period = KlineInterval.MIN15;

        webSocketClient.subMarketKline(symbol, period, (kline) -> {
            System.out.println("kline==" + kline.toString());
        });
    }

}
