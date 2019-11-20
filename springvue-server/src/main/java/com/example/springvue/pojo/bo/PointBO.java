package com.example.springvue.pojo.bo;

import java.util.Date;

/**
 * @Description: 积分
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/10/10 13:46
 */
public class PointBO {
    /**
     * 积分历史记录id
     */
    private Long id;

    /**
     * 积分变化量
     */
    private Integer score;

    /**
     * 记录时间
     */
    private Date createTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getScore() {
        return score;
    }

    public void setScore(Integer score) {
        this.score = score;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }
}
