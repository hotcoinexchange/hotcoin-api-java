package com.hotcoin.api.bean.websocket;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 买一卖一逐笔行情
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketBBO implements Serializable {

    /**
     * 币对：btc_usdt
     */
    private String symbol;

    /**
     * 买一价
     */
    private BigDecimal bid;

    /**
     * 买一量
     */
    private BigDecimal bidSize;

    /**
     * 卖一价
     */
    private BigDecimal ask;

    /**
     * 卖一量
     */
    private BigDecimal askSize;

    /**
     * 最新价
     */
    private BigDecimal last;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public BigDecimal getBid() {
        return bid;
    }

    public void setBid(BigDecimal bid) {
        this.bid = bid;
    }

    public BigDecimal getBidSize() {
        return bidSize;
    }

    public void setBidSize(BigDecimal bidSize) {
        this.bidSize = bidSize;
    }

    public BigDecimal getAsk() {
        return ask;
    }

    public void setAsk(BigDecimal ask) {
        this.ask = ask;
    }

    public BigDecimal getAskSize() {
        return askSize;
    }

    public void setAskSize(BigDecimal askSize) {
        this.askSize = askSize;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

}
