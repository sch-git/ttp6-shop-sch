package com.example.springvue.service.impl;

import com.example.springvue.dao.GoodImageMapper;
import com.example.springvue.pojo.dto.GoodImageDTO;
import com.example.springvue.pojo.vo.AdminVO;
import com.example.springvue.service.GoodImageService;
import com.example.springvue.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 商品图片
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 00:05
 */
@Service
public class GoodImageServiceImpl implements GoodImageService {
    @Autowired
    private GoodImageMapper goodImageMapper;
    @Autowired
    private HttpSession session;

    /**
     * 根据商品id获取商品图片
     *
     * @param goodId
     * @return
     */
    @Override
    public List<String> getImageUrlByGoodId(Long goodId) {
        List<String> urlList = goodImageMapper.getImageUrlByGoodId(goodId);
        return urlList;
    }

    /**
     * 保存商品图片url
     *
     * @param goodImageDTOS 图片数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodImageList(List<GoodImageDTO> goodImageDTOS) {
        String admin = "admin";
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + admin);
        for (GoodImageDTO goodImageDTO : goodImageDTOS) {
            goodImageDTO.setCreateBy(adminVO.getId());
            goodImageDTO.setCreateTime(DateUtil.createTime());
        }
        goodImageMapper.insertGoodImageList(goodImageDTOS);
    }
}
