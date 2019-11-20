package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.OrderItemBO;

/**
 * @Description: 前台订单详情
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/29 09:05
 */
public class OrderItemVO {
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 商品图片
     */
    private String url;

    public final void setBO(OrderItemBO orderItemBO) {
        this.id = orderItemBO.getId();
        this.name = orderItemBO.getName();
        this.number = orderItemBO.getNumber();
        this.price = orderItemBO.getPrice();
        this.url = orderItemBO.getUrl();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
