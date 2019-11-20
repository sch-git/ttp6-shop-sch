package com.example.springvue.service;

import com.example.springvue.pojo.vo.PointVO;

import java.util.List;

/**
 * @Description: TODO 积分
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/10/10 09:47
 */
public interface PointService {
    /**
     * 添加积分
     *
     * @param score 积分变化量
     */
    void addPoint(Integer score);

    /**
     * 查询用户积分历史记录
     *
     * @param userId 用户id
     * @return 用户积分历史记录
     */
    List<PointVO> getUserPoint(Long userId);
}
