package com.example.springvue.pojo.bo;

import java.util.Date;

/**
 * @Description: 后台查询订单
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/29 11:13
 */
public class OrderAdminBO {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单编号
     */
    private String orderNo;

    /**
     * 订单地址
     */
    private String address;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 收货人
     */
    private String receiver;

    /**
     * 订单总价
     */
    private Long totalPrice;

    /**
     * 收货人电话
     */
    private String phone;

    /**
     * 创建日期
     */
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getReceiver() {
        return receiver;
    }

    public void setReceiver(String receiver) {
        this.receiver = receiver;
    }

    public Long getTotalPrice() {
        return totalPrice;
    }

    public void setTotalPrice(Long totalPrice) {
        this.totalPrice = totalPrice;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
