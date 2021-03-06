package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.UserAO;

import java.util.Date;

/**
 * @Description: 用于数据库操作的对象
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/18 13:18
 */
public class UserDTO {
    /**
     * 用户id
     */
    private Long id;

    /**
     * 用户状态 0：无效 1：有效
     */
    private Boolean status;

    /**
     * 用户名/昵称
     */
    private String name;

    /**
     * 创建日期
     */
    private Date createDate;

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

    public UserDTO() {

    }

    public UserDTO(UserAO userAO) {
        this.id = userAO.getId();
        this.status = userAO.getStatus();
        this.name = userAO.getName();
        this.address = userAO.getAddress();
        this.phone = userAO.getPhone();
        this.email = userAO.getEmail();
        this.totalScore = userAO.getTotalScore();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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
}
