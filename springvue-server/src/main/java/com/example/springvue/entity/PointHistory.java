package com.example.springvue.entity;

import java.util.Date;

/**
 * @Description: 积分历史
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:56
 */
public class PointHistory {
    /**
     * 积分id
     */
    private Integer id;

    /**
     * 用户id
     */
    private Integer userId;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 总积分
     */
    private Integer score;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }
}