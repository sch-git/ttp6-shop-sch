package com.example.springvue.entity;

import java.util.Date;

/**
 * @Description: 用户
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/07 11:25
 */
public class User {
    /**
     * 用户id
     */
    private Integer id;

    /**
     * 用户状态 0：无效 1：有效
     */
    private Boolean status;

    /**
     * 用户名/昵称
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 地址
     */
    private String address;

    /**
     * 密码盐
     */
    private Byte salt;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 用户头像地址
     */
    private String headImageUrl;

    /**
     * 用户积分
     */
    private Long totalScore;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Byte getSalt() {
        return salt;
    }

    public void setSalt(Byte salt) {
        this.salt = salt;
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

    public String getHeadImageUrl() {
        return headImageUrl;
    }

    public void setHeadImageUrl(String headImageUrl) {
        this.headImageUrl = headImageUrl;
    }

    public Long getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Long totalScore) {
        this.totalScore = totalScore;
    }
}