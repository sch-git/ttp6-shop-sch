package com.example.springvue.pojo.bo;

/**
 * @Description: 订单简略显示
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/28 23:41
 */
public class OrderBO {
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
     * 电话
     */
    private String phone;

    /**
     * 该订单的商品数量
     */
    private Integer number;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
