package com.hotcoin.api.bean.websocket;

import com.alibaba.fastjson.JSON;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 盘口深度数据
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketDepth implements Serializable {

    /**
     * 买方深度
     * 数据结构: [["1","2"],["3","4"]]
     * 数据描述: arr[0]:价格  arr[1] : 数量
     */
    private List<List<String>> bids;

    /**
     * 卖方深度
     * 数据结构同上 数据描述同上
     */
    private List<List<String>> asks;

    /**
     * 最新价
     */
    private BigDecimal last;

    /**
     * 开盘价
     */
    private BigDecimal open;

    /**
     * 折合人民币价格
     */
    private BigDecimal cny;

    /**
     * ETF净值
     */
    private BigDecimal netValue;

    /**
     * 买入或者卖出人民币估值时使用
     */
    private BigDecimal buyOrSellCnyPrice;

    public List<List<String>> getBids() {
        return bids;
    }

    public void setBids(List<List<String>> bids) {
        this.bids = bids;
    }

    public List<List<String>> getAsks() {
        return asks;
    }

    public void setAsks(List<List<String>> asks) {
        this.asks = asks;
    }

    public BigDecimal getLast() {
        return last;
    }

    public void setLast(BigDecimal last) {
        this.last = last;
    }

    public BigDecimal getOpen() {
        return open;
    }

    public void setOpen(BigDecimal open) {
        this.open = open;
    }

    public BigDecimal getCny() {
        return cny;
    }

    public void setCny(BigDecimal cny) {
        this.cny = cny;
    }

    public BigDecimal getNetValue() {
        return netValue;
    }

    public void setNetValue(BigDecimal netValue) {
        this.netValue = netValue;
    }

    public BigDecimal getBuyOrSellCnyPrice() {
        return buyOrSellCnyPrice;
    }

    public void setBuyOrSellCnyPrice(BigDecimal buyOrSellCnyPrice) {
        this.buyOrSellCnyPrice = buyOrSellCnyPrice;
    }

    @Override
    public String toString() {
        return JSON.toJSONString(this);
    }

}
