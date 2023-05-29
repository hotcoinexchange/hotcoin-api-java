package com.hotcoin.api.bean.websocket;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 实时成交明细
 *
 * @author : hotcoin
 * @version : 1.0.0
 * @date: 2021/12/26 10:08
 */
public class MarketTradeDetail implements Serializable {

    /**
     * 交易时间 13位
     */
    private Long ts;

    /**
     * 交易数量
     */
    private BigDecimal amount;

    /**
     * 交易价格
     */
    private BigDecimal price;

    /**
     * 买卖类型，buy/sell
     */
    private String direction;

    public Long getTs() {
        return ts;
    }

    public void setTs(Long ts) {
        this.ts = ts;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDirection() {
        return direction;
    }

    public void setDirection(String direction) {
        this.direction = direction;
    }

}
