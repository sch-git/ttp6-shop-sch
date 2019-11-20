package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.OrderItemAO;
import lombok.Data;

/**
 * @Description: 订单项
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/27 16:05
 */
@Data
public class OrderItemDTO {
    /**
     * 订单id
     */
    private Long orderId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品名
     */
    private String goodName;

    /**
     * 商品购买数量
     */
    private Integer number;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品图片
     */
    private String url;

    public final void setAO(OrderItemAO orderItemAO) {
        this.orderId = orderItemAO.getOrderId();
        this.goodId = orderItemAO.getGoodId();
        this.goodName = orderItemAO.getGoodName();
        this.number = orderItemAO.getNumber();
        this.price = orderItemAO.getPrice();
        this.url = orderItemAO.getUrl();
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
