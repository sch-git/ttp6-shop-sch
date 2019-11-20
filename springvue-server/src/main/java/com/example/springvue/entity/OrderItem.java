package com.example.springvue.entity;

/**
 * @Description: 订单项
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:57
 */
public class OrderItem {
    /**
     * 订单项id
     */
    private Integer id;

    /**
     * 订单id
     */
    private Integer orderId;

    /**
     * 商品id
     */
    private Integer goodId;

    /**
     * 商品名
     */
    private String goodName;

    /**
     * 商品数
     */
    private Integer number;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品图片地址
     */
    private String url;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getOrderId() {
        return orderId;
    }

    public void setOrderId(Integer orderId) {
        this.orderId = orderId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
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