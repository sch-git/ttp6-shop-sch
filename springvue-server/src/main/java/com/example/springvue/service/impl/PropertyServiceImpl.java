package com.example.springvue.service.impl;

import com.example.springvue.dao.PropertyMapper;
import com.example.springvue.dao.PropertyValueMapper;
import com.example.springvue.pojo.bo.PropertyBO;
import com.example.springvue.pojo.bo.PropertyValueBO;
import com.example.springvue.pojo.dto.PropertyDTO;
import com.example.springvue.pojo.dto.PropertyValueDTO;
import com.example.springvue.pojo.vo.AdminVO;
import com.example.springvue.pojo.vo.PropertyVO;
import com.example.springvue.pojo.vo.PropertyValueVO;
import com.example.springvue.service.PropertyService;
import com.example.springvue.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 属性管理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/20 14:30
 */
@Service
public class PropertyServiceImpl implements PropertyService {
    @Autowired
    private PropertyMapper propertyMapper;
    @Autowired
    private PropertyValueMapper propertyValueMapper;
    @Autowired
    private HttpSession session;
    private static String adminStr = "admin";

    /**
     * 增加属性
     *
     * @param propertyDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addProperty(PropertyDTO propertyDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);
        propertyDTO.setCreateBy(adminVO.getId());
        propertyDTO.setLastModifyBy(adminVO.getId());
        propertyDTO.setCreateTime(DateUtil.createTime());
        propertyDTO.setLastModifyTime(DateUtil.createTime());
        propertyMapper.insertProperty(propertyDTO);
    }

    /**
     * 获取所有属性
     *
     * @return
     */
    @Override
    public List<PropertyVO> getPropertyList() {
        List<PropertyBO> propertyBOS = propertyMapper.getPropertyList();
        if (propertyBOS.size() > 0) {
            List<PropertyVO> propertyVOS = new ArrayList<>();
            for (PropertyBO propertyBO : propertyBOS) {
                PropertyVO propertyVO = new PropertyVO(propertyBO);
                propertyVOS.add(propertyVO);
            }
            return propertyVOS;
        } else {
            return null;
        }
    }

    /**
     * 修改属性名
     *
     * @param propertyDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public PropertyVO changePropertyName(PropertyDTO propertyDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);
        propertyDTO.setLastModifyBy(adminVO.getId());
        propertyDTO.setLastModifyTime(DateUtil.createTime());
        if (propertyMapper.updatePropertyName(propertyDTO) > 0) {
            return new PropertyVO(propertyDTO);
        } else {
            return null;
        }

    }

    /**
     * 模糊查询属性
     *
     * @param propertyDTO 查询条件
     * @return 属性列表
     */
    @Override
    public List<PropertyVO> queryProperty(PropertyDTO propertyDTO) {
        List<PropertyBO> propertyBOS = propertyMapper.getPropertyByParams(propertyDTO);
        if (propertyBOS.size() > 0) {
            List<PropertyVO> propertyVOS = new ArrayList<>();
            for (PropertyBO propertyBO : propertyBOS) {
                PropertyVO propertyVO = new PropertyVO(propertyBO);
                propertyVOS.add(propertyVO);
            }
            return propertyVOS;
        } else {
            return null;
        }
    }

    /**
     * 根据商品id查询没有的属性
     *
     * @param goodId 商品id
     * @return 商品没有的属性列表
     */
    @Override
    public List<PropertyBO> queryPropertyListByGoodId(Long goodId) {
        List<PropertyBO> propertyBOS = propertyMapper.getGoodNoPropertyList(goodId);
        return propertyBOS;
    }

    /**
     * 增加商品属性值
     *
     * @param propertyValueDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodPropertyValue(PropertyValueDTO propertyValueDTO) {

        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);
        propertyValueDTO.setCreateBy(adminVO.getId());
        propertyValueDTO.setCreateTime(DateUtil.createTime());
        propertyValueDTO.setLastModifyBy(adminVO.getId());
        propertyValueDTO.setLastModifyTime(DateUtil.createTime());

        propertyValueMapper.insertGoodPropertyValue(propertyValueDTO);
    }

    /**
     * 根据属性id查询所有属值
     *
     * @param propertyId
     * @return
     */
    @Override
    public List<PropertyValueVO> queryPropertyValueList(Long propertyId) {
        List<PropertyValueBO> propertyValueBOS = propertyValueMapper.getPropertyValueByPropertyId(propertyId);
        List<PropertyValueVO> propertyValueVOS = new ArrayList<>();
        for (PropertyValueBO propertyValueBO : propertyValueBOS) {
            PropertyValueVO propertyValueVO = new PropertyValueVO();
            propertyValueVO.setBO(propertyValueBO);
            propertyValueVOS.add(propertyValueVO);
        }
        return propertyValueVOS;
    }

    /**
     * 根据id修改属性值
     *
     * @param propertyValueDTO 修改数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changePropertyValueById(PropertyValueDTO propertyValueDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);

        propertyValueDTO.setLastModifyBy(adminVO.getId());
        propertyValueDTO.setLastModifyTime(DateUtil.createTime());
        propertyValueMapper.updatePropertyValueById(propertyValueDTO);
    }
}
