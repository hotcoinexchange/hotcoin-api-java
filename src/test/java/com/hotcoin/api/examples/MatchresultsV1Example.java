package com.hotcoin.api.examples;

import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.utils.HotcoinRestConnection;
import com.hotcoin.api.utils.UrlBuilder;

/**
 * 历史成交明细
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2022/3/26 23:14
 */
public class MatchresultsV1Example {

    public static void main(String[] args) {
        HotcoinRestConnection hotcoinRestConnection = new HotcoinRestConnection(APIConfiguration.buildGlobal());
        UrlBuilder urlBuilder = UrlBuilder.build();
        urlBuilder.putToUrl("symbol", "btc_usdt");
        String uri = "/v1/order/matchresults";

        String result = hotcoinRestConnection.executeSignedGet(uri, urlBuilder);

        System.out.println(result);
    }

}
