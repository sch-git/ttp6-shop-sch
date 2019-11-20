package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.OrderAdminBO;
import com.example.springvue.util.DateUtil;

/**
 * @Description: 后台查询订单
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/29 11:08
 */
public class OrderAdminVO {
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
    private String createDate;

    public final void setBO(OrderAdminBO orderAdminBO) {
        this.address = orderAdminBO.getAddress();
        this.createDate = DateUtil.createTimeVO(orderAdminBO.getCreateDate());
        this.id = orderAdminBO.getId();
        this.orderNo = orderAdminBO.getOrderNo();
        this.phone = orderAdminBO.getPhone();
        this.receiver = orderAdminBO.getReceiver();
        this.status = orderAdminBO.getStatus();
        this.totalPrice = orderAdminBO.getTotalPrice();
    }

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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
        this.createDate = createDate;
    }
}
