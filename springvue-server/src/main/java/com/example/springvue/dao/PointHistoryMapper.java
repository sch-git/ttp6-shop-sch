package com.example.springvue.dao;

import com.example.springvue.pojo.bo.PointBO;
import com.example.springvue.pojo.dto.PointDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 积分
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:56
 */
@Repository
public interface PointHistoryMapper {

    /**
     * 更新积分
     *
     * @param pointDTO 积分信息
     */
    void updateScore(PointDTO pointDTO);

    /**
     * 查询用户积分历史记录
     *
     * @param userId 用户id
     * @return 用户积分历史记录
     */
    List<PointBO> getPointHistory(Long userId);
}