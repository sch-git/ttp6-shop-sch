package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.OrderAO;

import java.util.Date;

/**
 * @Description: 订单
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/27 14:19
 */
public class OrderDTO {

    /**
     * 订单id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 创建时间
     */
    private Date createDate;

    /**
     * 支付时间
     */
    private Date payDate;

    /**
     * 取消订单时间
     */
    private Date deleteTime;

    /**
     * 收货地址
     */
    private String address;

    /**
     * 收货人姓名
     */
    private String receiver;

    /**
     * 收货手机号
     */
    private String phone;

    /**
     * 订单总价
     */
    private Long totalPrice;

    public final void setAO(OrderAO orderAO) {
        this.address = orderAO.getAddress();
        this.receiver = orderAO.getReceiver();
        this.phone = orderAO.getPhone();
        this.totalPrice = orderAO.getTotalPrice();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
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
