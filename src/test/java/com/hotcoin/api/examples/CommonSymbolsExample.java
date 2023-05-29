package com.hotcoin.api.examples;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.utils.HotcoinRestConnection;
import com.hotcoin.api.utils.UrlBuilder;

/**
 * 交易对信息
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 23:14
 */
public class CommonSymbolsExample {

    public static void main(String[] args) {
        HotcoinRestConnection hotcoinRestConnection = new HotcoinRestConnection(APIConfiguration.buildGlobal());
        UrlBuilder urlBuilder = UrlBuilder.build();
        String uri = "/v1/common/symbols";

        String result = hotcoinRestConnection.executeGet(uri, urlBuilder);

        System.out.println(result);
    }

}
