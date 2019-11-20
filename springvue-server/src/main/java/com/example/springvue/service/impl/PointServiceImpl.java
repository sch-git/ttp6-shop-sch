package com.example.springvue.service.impl;

import com.example.springvue.dao.PointHistoryMapper;
import com.example.springvue.pojo.bo.PointBO;
import com.example.springvue.pojo.dto.PointDTO;
import com.example.springvue.pojo.vo.PointVO;
import com.example.springvue.pojo.vo.UserVO;
import com.example.springvue.service.PointService;
import com.example.springvue.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO 积分
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/10/10 09:47
 */
@Service
public class PointServiceImpl implements PointService {
    @Autowired
    private HttpSession session;
    @Autowired
    private PointHistoryMapper pointHistoryMapper;

    /**
     * 添加积分
     *
     * @param score 积分变化量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addPoint(Integer score) {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        PointDTO pointDTO = new PointDTO();
        pointDTO.setUserId(userVO.getId());
        pointDTO.setUserName(userVO.getName());
        pointDTO.setCreateTime(DateUtil.createTime());
        pointDTO.setScore(score);
        pointHistoryMapper.updateScore(pointDTO);
    }

    /**
     * 查询用户积分历史记录
     *
     * @param userId 用户id
     * @return 用户积分历史记录
     */
    @Override
    public List<PointVO> getUserPoint(Long userId) {
        List<PointVO> pointVOList = new ArrayList<>();
        List<PointBO> pointBOList = pointHistoryMapper.getPointHistory(userId);
        for (PointBO pointBO : pointBOList) {
            PointVO pointVO = new PointVO();
            pointVO.setBO(pointBO);
            pointVOList.add(pointVO);
        }
        return pointVOList;
    }
}
