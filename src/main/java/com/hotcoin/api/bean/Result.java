package com.hotcoin.api.bean;

import java.io.Serializable;

/**
 * Rest Api返回结果
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class Result implements Serializable {

    /**
     * 状态码 200 成功
     */
    public static final int SUCCESS = 200;

    /**
     * 业务自定义状态码，200表示成功
     */
    public int code;

    /**
     * 状态描述
     */
    public String msg;

    /**
     * 时间戳 13位
     */
    public Long time;

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

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
