package com.hotcoin.api.utils;

import com.hotcoin.api.exceptions.HotcoinApiException;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.zip.GZIPInputStream;

/**
 * @author hotcoin
 * @version 1.0
 * @description: JDK自带Gzip压缩工具类封装
 * @date 2021/12/26 10:08
 */
public class WebSocketUtils {

    /**
     * connectionIdGenerator
     * connectionId生成器
     */
    private static final AtomicInteger CONNECTION_ID_GENERATOR = new AtomicInteger();

    /**
     * Gzip解压
     *
     * @param bytes
     * @return 如果异常返回null
     */
    public static byte[] decompress(byte[] bytes) throws IOException {
        if (bytes == null || bytes.length == 0) {
            return new byte[0];
        }
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        ByteArrayInputStream in = new ByteArrayInputStream(bytes);
        GZIPInputStream gzip = new GZIPInputStream(in);
        byte[] buffer = new byte[1024];
        int n;
        while ((n = gzip.read(buffer)) != -1) {
            out.write(buffer, 0, n);
        }
        return out.toByteArray();
    }

    /**
     * 等待milliseconds
     *
     * @param millis
     * @throws HotcoinApiException
     */
    public static void await(long millis) throws HotcoinApiException {
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            throw new HotcoinApiException(HotcoinApiException.SYSTEM_ERROR, "Error when sleep");
        }
    }

    /**
     * Get ConnectionId
     * 获取连接id
     *
     * @return
     */
    public static int getConnectionId() {
        return CONNECTION_ID_GENERATOR.incrementAndGet();
    }

}
