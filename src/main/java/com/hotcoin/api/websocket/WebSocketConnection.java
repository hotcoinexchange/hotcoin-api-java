package com.hotcoin.api.websocket;

import com.hotcoin.api.enums.ConnectionState;

/**
 * Hotcoin WebSocket Connection
 * Hotcoin WebSocket 连接接口
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public interface WebSocketConnection {

    /**
     * Get Connection State
     * 获取连接状态
     *
     * @return
     */
    ConnectionState getState();

    /**
     * Get ConnectionId
     * 获取连接ID
     *
     * @return
     */
    Integer getConnectionId();

    /**
     * WebSocket Reconnect
     * WebSocket重连
     */
    void reConnect();

    /**
     * WebSocket Reconnect
     * WebSocket重连
     *
     * @param delayInSecond
     */
    void reConnect(int delayInSecond);

    /**
     * 关闭连接
     */
    void close();

    /**
     * Get LastReceivedTime
     * 获取上次接收时间
     *
     * @return
     */
    long getLastReceivedTime();

    /**
     * Send Data To Server
     * 往服务器发送数据
     *
     * @param data
     */
    void send(String data);

}
