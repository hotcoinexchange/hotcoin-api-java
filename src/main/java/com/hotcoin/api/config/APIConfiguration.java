package com.hotcoin.api.config;

import com.hotcoin.api.constant.HotcoinApiUrls;
import com.hotcoin.api.constant.PrivateApiConfig;
import com.hotcoin.api.enums.I18n;
import lombok.*;

/**
 * Hotcoin API Configuration
 * Hotcoin API配置
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class APIConfiguration {

    /**
     * Access Key
     * {@link com.hotcoin.api.constant.PrivateApiConfig}
     */
    private String accessKey = PrivateApiConfig.ACCESS_KEY;

    /**
     * Secret Key
     * {@link com.hotcoin.api.constant.PrivateApiConfig}
     */
    private String secretKey = PrivateApiConfig.SECRET_KEY;

    /**
     * Rest api endpoint url
     * {@link com.hotcoin.api.constant.HotcoinApiUrls}
     */
    private String restEndpoint = HotcoinApiUrls.SPOT_REST_URL;

    /**
     * websocket api endpoint url
     * {@link com.hotcoin.api.constant.HotcoinApiUrls}
     */
    private String webSocketEndpoint = HotcoinApiUrls.SPOT_WS_URL;

    /**
     * connection timeout.
     */
    private long connectTimeout = 5000;

    /**
     * read timeout.
     */
    private long readTimeout = 5000;

    /**
     * write timeout.
     */
    private long writeTimeout = 5000;

    /**
     * internationalization  {@link I18n}
     */
    private I18n i18n = I18n.EN_US;

    /**
     * 创建默认全局配置
     * Create Global Configuration
     *
     * @return
     */
    public static APIConfiguration buildGlobal() {
        return new APIConfiguration();
    }

    /**
     * 创建Rest Api自定义配置
     * Create Rest Api Customized Configuration
     *
     * @param accessKey
     * @param secretKey
     * @param restEndpoint
     * @return
     */
    public static APIConfiguration buildRestConfig(String accessKey, String secretKey, String restEndpoint) {
        APIConfiguration config = new APIConfiguration();
        config.setAccessKey(accessKey);
        config.setSecretKey(secretKey);
        config.setRestEndpoint(restEndpoint);
        return config;
    }

    /**
     * 创建WebSocket Api自定义配置
     * Create WebSocket Api Customized Configuration
     *
     * @param accessKey
     * @param secretKey
     * @param webSocketEndpoint
     * @return
     */
    public static APIConfiguration buildWsConfig(String accessKey, String secretKey, String webSocketEndpoint) {
        APIConfiguration config = new APIConfiguration();
        config.setAccessKey(accessKey);
        config.setSecretKey(secretKey);
        config.setWebSocketEndpoint(webSocketEndpoint);
        return config;
    }

}
