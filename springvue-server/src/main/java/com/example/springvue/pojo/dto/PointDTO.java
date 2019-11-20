package com.example.springvue.pojo.dto;

import java.util.Date;

/**
 * @Description: 积分
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/10/10 10:00
 */
public class PointDTO {
    /**
     * 积分id
     */
    private Long id;

    /**
     * 用户id
     */
    private Long userId;

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

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
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
