package com.example.springvue.dao;

import com.example.springvue.pojo.bo.PropertyValueBO;
import com.example.springvue.pojo.dto.PropertyValueDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:51
 */
@Repository
public interface PropertyValueMapper {

    /**
     * 增加商品属性值
     *
     * @param propertyValueDTO
     * @return
     */
    Integer insertGoodPropertyValue(PropertyValueDTO propertyValueDTO);

    /**
     * 根据属性id查询所有属值
     *
     * @param propertyId
     * @return
     */
    List<PropertyValueBO> getPropertyValueByPropertyId(Long propertyId);

    /**
     * 根据id修改属性值
     *
     * @param propertyValueDTO 修改的数据
     * @return
     */
    Integer updatePropertyValueById(PropertyValueDTO propertyValueDTO);
}