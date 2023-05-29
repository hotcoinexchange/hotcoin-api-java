package com.hotcoin.api.bean.websocket;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 交易对行情
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketTicker implements Serializable {

    /**
     * 卖方币种简称：eg:BTC
     */
    private String sellShortName;

    /**
     * 卖方币种全称：eg:bitcoin
     */
    private String sellSymbol;

    /**
     * 买方币种简称：eg:ETH
     */
    private String buyShortName;

    /**
     * 买方币种全称:eg：ethereum
     */
    private String buySymbol;

    /**
     * 最高价
     */
    private BigDecimal high;

    /**
     * 开盘价
     */
    private BigDecimal open;

    /**
     * 最低价
     */
    private BigDecimal low;

    /**
     * 收盘价
     */
    private BigDecimal close;

    /**
     * 交易量
     */
    private BigDecimal volume;

    /**
     * 涨跌幅
     */
    private BigDecimal change;

    /**
     * 折合人民币的最新价
     */
    private BigDecimal cny;

    /**
     * 币对id
     */
    private Integer tradeId;

    /**
     * 最新价(原始价格)
     */
    private BigDecimal last;

    /**
     * ETF净值
     */
    private BigDecimal netValue;

    public String getSellShortName() {
        return sellShortName;
    }

    public void setSellShortName(String sellShortName) {
        this.sellShortName = sellShortName;
    }

    public String getSellSymbol() {
        return sellSymbol;
    }

    public void setSellSymbol(String sellSymbol) {
        this.sellSymbol = sellSymbol;
    }

    public String getBuyShortName() {
        return buyShortName;
    }

    public void setBuyShortName(String buyShortName) {
        this.buyShortName = buyShortName;
    }

    public String getBuySymbol() {
        return buySymbol;
    }

    public void setBuySymbol(String buySymbol) {
        this.buySymbol = buySymbol;
    }

    public BigDecimal getHigh() {
        return high;
    }

    public void setHigh(BigDecimal high) {
        this.high = high;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getLow() {
        return low;
    }

    public void setLow(BigDecimal low) {
        this.low = low;
    }

    public BigDecimal getClose() {
        return close;
    }

    public void setClose(BigDecimal close) {
        this.close = close;
    }

    public BigDecimal getVolume() {
        return volume;
    }

    public void setVolume(BigDecimal volume) {
        this.volume = volume;
    }

    public BigDecimal getChange() {
        return change;
    }

    public void setChange(BigDecimal change) {
        this.change = change;
    }

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public Integer getTradeId() {
        return tradeId;
    }

    public void setTradeId(Integer tradeId) {
        this.tradeId = tradeId;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

}
