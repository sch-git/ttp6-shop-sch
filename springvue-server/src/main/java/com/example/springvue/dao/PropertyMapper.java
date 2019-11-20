package com.example.springvue.dao;

import com.example.springvue.pojo.bo.PropertyBO;
import com.example.springvue.pojo.dto.PropertyDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:42
 */
@Repository
public interface PropertyMapper {

    /**
     * 新增属性
     *
     * @param propertyDTO
     * @return
     */
    Integer insertProperty(PropertyDTO propertyDTO);

    /**
     * 查询所有属性
     *
     * @return
     */
    List<PropertyBO> getPropertyList();

    /**
     * 修改属性名
     *
     * @param propertyDTO
     * @return
     */
    Integer updatePropertyName(PropertyDTO propertyDTO);

    /**
     * 模糊查询属性
     *
     * @param propertyDTO 查询条件
     * @return 属性列表
     */
    List<PropertyBO> getPropertyByParams(PropertyDTO propertyDTO);

    /**
     * 查询商品所没有的属性
     *
     * @param goodId 商品id
     * @return 商品没有的属性集合
     */
    List<PropertyBO> getGoodNoPropertyList(Long goodId);
}