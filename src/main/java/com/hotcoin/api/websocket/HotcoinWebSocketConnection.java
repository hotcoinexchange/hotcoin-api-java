package com.hotcoin.api.websocket;

import com.alibaba.fastjson.JSON;
import com.hotcoin.api.config.APIConfiguration;
import com.hotcoin.api.constant.ApiConstants;
import com.hotcoin.api.enums.ConnectionState;
import com.hotcoin.api.utils.ConnectionFactory;
import com.hotcoin.api.utils.WebSocketUtils;
import lombok.Data;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Hotcoin WebSocket Connection
 * Hotcoin WebSocket 连接接口实现
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
@Data
public class HotcoinWebSocketConnection<T> extends WebSocketListener implements WebSocketConnection {

    private static final Logger LOG = LoggerFactory.getLogger(HotcoinWebSocketConnection.class);

    /**
     * 上次接收时间
     */
    private long lastReceivedTime;

    /**
     * webSocket
     */
    private WebSocket webSocket;

    /**
     * request
     */
    private Request request;

    /**
     * 连接状态
     */
    private ConnectionState state;

    /**
     * 连接ID
     */
    private Integer connectionId;

    /**
     * 发送命令
     */
    private String command;

    /**
     * 异步回调
     */
    private WebSocketCallback callback;

    /**
     * 回调数据类型
     */
    private Class<T> clazz;

    /**
     * 自动断开
     */
    private boolean autoClose;

    /**
     * 是否认证
     */
    private boolean auth;

    /**
     * API配置
     */
    private APIConfiguration config;

    /**
     * 延迟(Second)
     */
    private long delayInSecond;

    private Object mutex = new Object();

    private HotcoinWebSocketConnection() {
    }

    /**
     * Create Market WebSocket Connection
     * 创建行情WebSocket连接
     *
     * @param config
     * @param command
     * @param callback
     * @return
     */
    public static HotcoinWebSocketConnection createMarketConnection(APIConfiguration config,
                                                                    String command,
                                                                    WebSocketCallback callback,
                                                                    Class clazz) {
        return createConnection(config, command, callback, clazz, Boolean.FALSE, Boolean.FALSE);
    }

    /**
     * Create Market WebSocket Connection
     * 创建行情WebSocket连接
     *
     * @param config
     * @param command
     * @param callback
     * @param autoClose
     * @param auth
     * @return
     */
    public static HotcoinWebSocketConnection createConnection(APIConfiguration config,
                                                              String command,
                                                              WebSocketCallback callback,
                                                              Class clazz,
                                                              Boolean autoClose,
                                                              Boolean auth) {
        HotcoinWebSocketConnection connection = new HotcoinWebSocketConnection();
        connection.setConfig(config);
        connection.setCommand(command);
        connection.setCallback(callback);
        connection.setClazz(clazz);
        connection.setAutoClose(autoClose);
        connection.setAuth(auth);
        connection.setConnectionId(WebSocketUtils.getConnectionId());
        connection.setRequest(new Request.Builder().url(config.getWebSocketEndpoint()).build());
        // 创建连接
        connection.connect();
        return connection;
    }

    @Override
    public void reConnect(int delayInSecond) {
        if (LOG.isWarnEnabled()) {
            LOG.warn("[Sub][" + this.getConnectionId() + "] Reconnecting after "
                    + delayInSecond + " seconds later");
        }
        if (webSocket != null) {
            webSocket.cancel();
            webSocket = null;
        }
        this.delayInSecond = delayInSecond;
        state = ConnectionState.DELAY_CONNECT;
    }

    @Override
    public void close() {
        LOG.error("[Connection close][" + this.getConnectionId() + "] Closing normally");
        webSocket.cancel();
        webSocket = null;
        WebSocketWatchDog.onClosedNormally(this);
    }

    @Override
    public void reConnect() {
        if (delayInSecond != 0) {
            delayInSecond--;
        } else {
            connect();
        }
    }

    @Override
    public long getLastReceivedTime() {
        return this.lastReceivedTime;
    }

    @Override
    public void send(String str) {
        boolean result = false;
        if (LOG.isInfoEnabled()) {
            LOG.info("[Connection Send]{}", str);
        }
        if (webSocket != null) {
            result = webSocket.send(str);
        }
        if (!result) {
            LOG.error("[Connection Send][" + this.getConnectionId() + "] Failed to send message");
            closeOnError();
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, String text) {
        super.onMessage(webSocket, text);
        lastReceivedTime = System.currentTimeMillis();
        if (LOG.isDebugEnabled()) {
            LOG.debug("[On Message Text]:{}", text);
        }
        if (text.startsWith(ApiConstants.PING)) {
            send(ApiConstants.PONG);
        } else {
            callback.onReceive(JSON.parseObject(text, clazz));
        }
    }

    @Override
    public void onMessage(WebSocket webSocket, ByteString bytes) {
        super.onMessage(webSocket, bytes);
        try {
            lastReceivedTime = System.currentTimeMillis();
            String text;
            try {
                text = new String(WebSocketUtils.decompress(bytes.toByteArray()));
            } catch (IOException ex) {
                LOG.error("[Connection On Message][" + this.getConnectionId() + "] Receive message error: " + ex.getMessage());
                closeOnError();
                return;
            }
            if (LOG.isInfoEnabled()) {
                LOG.info("[Connection On Message][{}] {}", this.getConnectionId(), text);
            }
            if (text.startsWith(ApiConstants.PING)) {
                send(ApiConstants.PONG);
            } else {
                callback.onReceive(JSON.parseObject(text, clazz));
            }
        } catch (Exception e) {
            LOG.error("[Connection On Message][" + this.getConnectionId() + "] Unexpected error: " + e.getMessage());
            closeOnError();
        }
    }

    @Override
    public ConnectionState getState() {
        return state;
    }

    @Override
    public Integer getConnectionId() {
        return connectionId;
    }

    @Override
    public void onClosed(WebSocket webSocket, int code, String reason) {
        super.onClosed(webSocket, code, reason);
        if (state == ConnectionState.CONNECTED) {
            state = ConnectionState.IDLE;
        }
    }

    @Override
    public void onOpen(WebSocket webSocket, Response response) {
        super.onOpen(webSocket, response);
        this.webSocket = webSocket;
        if (LOG.isInfoEnabled()) {
            LOG.info("[Connection][" + this.getConnectionId() + "] Connected to server");
        }
        WebSocketWatchDog.onConnectionCreated(this);
        state = ConnectionState.CONNECTED;
        lastReceivedTime = System.currentTimeMillis();
        if (StringUtils.isNotBlank(command)) {
            // 发送命令
            send(command);
        }
    }

    @Override
    public void onFailure(WebSocket webSocket, Throwable t, Response response) {
        onError("Unexpected error: " + t.getMessage(), t);
        closeOnError();
    }

    /**
     * 创建连接
     */
    void connect() {
        if (state == ConnectionState.CONNECTED) {
            if (LOG.isInfoEnabled()) {
                LOG.info("[Connection][" + this.getConnectionId() + "] Already connected");
            }
            return;
        }
        if (LOG.isInfoEnabled()) {
            LOG.info("[Connection][" + this.getConnectionId() + "] Connecting...");
        }
        synchronized (mutex) {
            webSocket = ConnectionFactory.createWebSocket(request, this);
        }
    }

    /**
     * 错误时关闭
     */
    private void closeOnError() {
        if (webSocket != null) {
            this.webSocket.cancel();
            state = ConnectionState.CLOSED_ON_ERROR;
            LOG.error("[Connection error][" + this.getConnectionId() + "] Connection is closing due to error");
        }
    }

    /**
     * 错误时关闭
     */
    private void onError(String errorMessage, Throwable ex) {
        LOG.error("[Connection error][" + this.getConnectionId() + "] " + errorMessage);
        closeOnError();
    }

}
