package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.PointBO;
import com.example.springvue.util.DateUtil;

/**
 * @Description: 积分
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/10/10 13:47
 */
public class PointVO {
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
    private String createTime;

    public final void setBO(PointBO pointBO) {
        this.id = pointBO.getId();
        this.score = pointBO.getScore();
        this.createTime = DateUtil.createTimeVO(pointBO.getCreateTime());
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }
}
