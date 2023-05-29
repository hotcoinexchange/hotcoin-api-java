package com.hotcoin.api.websocket;

/**
 * Hotcoin WebSocket Callback
 * Hotcoin WebSocket 回调接口
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
@FunctionalInterface
public interface WebSocketCallback<T> {

    /**
     * 当成功接收到数据时回调
     * Be called when data is received from server successfully
     */
    void onReceive(T data);

}
