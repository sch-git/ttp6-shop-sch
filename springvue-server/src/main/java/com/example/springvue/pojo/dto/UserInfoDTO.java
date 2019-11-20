package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.UserInfoAO;

/**
 * @Description: 前台用户数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/25 10:36
 */
public class UserInfoDTO {
    /**
     * 用户id
     */
    private Long id;

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

    public void setAO(UserInfoAO userInfoAO) {
        this.name = userInfoAO.getName();
        this.address = userInfoAO.getAddress();
        this.phone = userInfoAO.getPhone();
        this.email = userInfoAO.getEmail();
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
