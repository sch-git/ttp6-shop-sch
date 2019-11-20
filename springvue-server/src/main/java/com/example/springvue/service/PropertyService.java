package com.example.springvue.service;

import com.example.springvue.pojo.bo.PropertyBO;
import com.example.springvue.pojo.dto.PropertyDTO;
import com.example.springvue.pojo.dto.PropertyValueDTO;
import com.example.springvue.pojo.vo.PropertyVO;
import com.example.springvue.pojo.vo.PropertyValueVO;

import java.util.List;

/**
 * @Description: 属性管理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/20 14:29
 */
public interface PropertyService {
    /**
     * 增加属性
     *
     * @param propertyDTO
     * @return
     */
    void addProperty(PropertyDTO propertyDTO);

    /**
     * 获取所有属性
     *
     * @return
     */
    List<PropertyVO> getPropertyList();

    /**
     * 修改属性名
     *
     * @param propertyDTO
     * @return
     */
    PropertyVO changePropertyName(PropertyDTO propertyDTO);

    /**
     * 模糊查询属性
     *
     * @param propertyDTO 查询条件
     * @return 属性列表
     */
    List<PropertyVO> queryProperty(PropertyDTO propertyDTO);

    /**
     * 根据商品id查询没有的属性
     *
     * @param goodId 商品id
     * @return 商品没有的属性列表
     */
    List<PropertyBO> queryPropertyListByGoodId(Long goodId);

    /**
     * 增加商品属性值
     *
     * @param propertyValueDTO
     * @return
     */
    void addGoodPropertyValue(PropertyValueDTO propertyValueDTO);

    /**
     * 根据属性id查询所有属值
     *
     * @param propertyId
     * @return
     */
    List<PropertyValueVO> queryPropertyValueList(Long propertyId);

    /**
     * 根据id修改属性值
     *
     * @param propertyValueDTO 修改数据
     */
    void changePropertyValueById(PropertyValueDTO propertyValueDTO);
}
