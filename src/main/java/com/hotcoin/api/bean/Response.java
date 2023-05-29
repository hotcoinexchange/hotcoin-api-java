package com.hotcoin.api.bean;

import java.io.Serializable;

/**
 * WebSocket响应结果
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class Response implements Serializable {

    /**
     * 业务自定义状态码，200表示成功
     */
    public int code;

    /**
     * 状态描述
     */
    public String msg;

    /**
     * 状态
     */
    public String status;

    /**
     * 时间戳 13位
     */
    public long ts;

    /**
     * 订阅主题
     */
    public String ch;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getTs() {
        return ts;
    }

    public void setTs(long ts) {
        this.ts = ts;
    }

    public String getCh() {
        return ch;
    }

    public void setCh(String ch) {
        this.ch = ch;
    }

}
