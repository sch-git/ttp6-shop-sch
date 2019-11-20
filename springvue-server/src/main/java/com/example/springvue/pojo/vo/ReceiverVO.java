package com.example.springvue.pojo.vo;

/**
 * @Description: 收货人信息
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/27 11:21
 */
public class ReceiverVO {
    /**
     * 收货人姓名
     */
    private String name;

    /**
     * 收货人手机号
     */
    private String phone;

    /**
     * 收货人地址
     */
    private String address;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
