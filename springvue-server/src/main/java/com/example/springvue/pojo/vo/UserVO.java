package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.UserBO;
import com.example.springvue.util.DateUtil;

/**
 * @Description: 返回前端的和user有关的数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 17:42
 */
public class UserVO {
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
    private String createDate;

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

    public UserVO() {

    }

    /**
     * 构造函数，将bo中的日期转换为字符串类型
     *
     * @param userBO
     */
    public UserVO(UserBO userBO) {
        this.id = userBO.getId();
        this.address = userBO.getAddress();
        this.createDate = DateUtil.createTimeVO(userBO.getCreateDate());
        this.email = userBO.getEmail();
        this.phone = userBO.getPhone();
        this.name = userBO.getName();
        this.status = userBO.getStatus();
        this.totalScore = userBO.getTotalScore();
    }

    public void setBO(UserBO userBO) {
        this.id = userBO.getId();
        this.address = userBO.getAddress();
        this.createDate = DateUtil.createTimeVO(userBO.getCreateDate());
        this.email = userBO.getEmail();
        this.phone = userBO.getPhone();
        this.name = userBO.getName();
        this.status = userBO.getStatus();
        this.totalScore = userBO.getTotalScore();
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

    public String getCreateDate() {
        return createDate;
    }

    public void setCreateDate(String createDate) {
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
