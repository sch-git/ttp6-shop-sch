package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.UserInfoBO;

/**
 * @Description: 前台用户数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/25 11:45
 */
public class UserInfoVO {
    /**
     * 用户名/昵称
     */
    private String name;

    /**
     * 地址
     */
    private String address;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 总积分
     */
    private Long totalScore;

    /**
     * 用户头像地址
     */
    private String headImageUrl;

    public void setBO(UserInfoBO userInfoBO) {
        this.name = userInfoBO.getName();
        this.address = userInfoBO.getAddress();
        this.phone = userInfoBO.getPhone();
        this.email = userInfoBO.getEmail();
        this.totalScore = userInfoBO.getTotalScore();
        this.headImageUrl = userInfoBO.getHeadImageUrl();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }
}
