package com.example.springvue.entity;

import java.util.Date;

/**
 * @Description: 订单
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:40
 */
public class Order {
    /**
     * 订单id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 订单地址
     */
    private String address;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;

    /**
     * 取消时间
     */
    private Date deleteTime;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 总价
     */
    private Long totalPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }
}