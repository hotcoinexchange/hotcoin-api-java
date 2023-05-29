package com.hotcoin.api.enums;

/**
 * Hotcoin WebSocket Connection State
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public enum ConnectionState {

    IDLE,

    DELAY_CONNECT,

    CONNECTED,

    CLOSED_ON_ERROR

}
