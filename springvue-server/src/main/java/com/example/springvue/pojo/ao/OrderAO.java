package com.example.springvue.pojo.ao;

/**
 * @Description: 创建订单数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/27 14:14
 */
public class OrderAO {

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
