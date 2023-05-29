package com.hotcoin.api.examples;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.utils.HotcoinRestConnection;
import com.hotcoin.api.utils.RandomUtils;
import com.hotcoin.api.utils.UrlBuilder;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 批量下单V1
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/28 23:26
 */
public class BatchOrdersV2Example {

    public static void main(String[] args) {
        HotcoinRestConnection hotcoinRestConnection = new HotcoinRestConnection(APIConfiguration.buildGlobal());
        UrlBuilder urlBuilder = UrlBuilder.build();
        String uri = "/v1/order/batchOrders";
        // 订单1
        List<Map<String, Object>> list = new ArrayList<>();
        Map<String, Object> order1 = new HashMap<>();
        order1.put("symbol", "eth_usdt");
        order1.put("type", "buy");
        order1.put("tradePrice", "1633.22");
        order1.put("tradeAmount", "0.1214");
        order1.put("clientOrderId", RandomUtils.get32UUID());
        list.add(order1);
        // 订单2
        Map<String, Object> order2 = new HashMap<>();
        order2.put("symbol", "eth_usdt");
        order2.put("type", "buy");
        order2.put("tradePrice", "1633.11");
        order2.put("tradeAmount", "0.2121");
        order2.put("clientOrderId", RandomUtils.get32UUID());
        list.add(order2);

        urlBuilder.putToPost(list);
        String result = hotcoinRestConnection.executeSignedPost(uri, urlBuilder);

        System.out.println(result);
    }

}
