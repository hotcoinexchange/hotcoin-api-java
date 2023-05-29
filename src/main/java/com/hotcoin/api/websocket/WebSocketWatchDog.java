package com.hotcoin.api.websocket;

import com.hotcoin.api.enums.ConnectionState;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * Hotcoin WebSocket WatchDog
 * Hotcoin WebSocket 连接监控
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class WebSocketWatchDog {

    private static final Logger LOG = LoggerFactory.getLogger(WebSocketWatchDog.class);

    /**
     * WebSocketConnection Collection
     */
    private static final Map<Integer, WebSocketConnection> CONNECTIONS = new ConcurrentHashMap<>();

    /**
     * 监控线程池线程数
     */
    public static final int CORE_POOL_SIZE = 1;

    /**
     * 连接断开重连阈值
     */
    public static final long NO_RESPONSE_THRESHOLD = 30_000;

    /**
     * 连接检测间隔
     */
    public static final long INITIAL_DELAY = 3_000;

    /**
     * 重连延迟时间
     */
    public static final int DELAY_ON_FAILURE = 15;

    static {
        ScheduledExecutorService executor = Executors.newScheduledThreadPool(CORE_POOL_SIZE);
        executor.scheduleAtFixedRate(() -> CONNECTIONS.entrySet().forEach(entry -> {
            WebSocketConnection connection = entry.getValue();
            if (connection.getState() == ConnectionState.CONNECTED) {
                long timestamp = System.currentTimeMillis() - connection.getLastReceivedTime();
                if (timestamp > NO_RESPONSE_THRESHOLD) {
                    LOG.warn("[Sub][" + connection.getConnectionId() + "] No response from server");
                    connection.reConnect(DELAY_ON_FAILURE);
                }
            } else if (connection.getState() == ConnectionState.DELAY_CONNECT) {
                connection.reConnect();
            } else if (connection.getState() == ConnectionState.CLOSED_ON_ERROR) {
                connection.reConnect(DELAY_ON_FAILURE);
            }
        }), INITIAL_DELAY, INITIAL_DELAY, TimeUnit.MILLISECONDS);
        Runtime.getRuntime().addShutdownHook(new Thread(executor::shutdown));
    }

    private WebSocketWatchDog() {
    }

    public static void onConnectionCreated(WebSocketConnection connection) {
        CONNECTIONS.put(connection.getConnectionId(), connection);
    }

    public static void onClosedNormally(WebSocketConnection connection) {
        CONNECTIONS.remove(connection);
    }

}
