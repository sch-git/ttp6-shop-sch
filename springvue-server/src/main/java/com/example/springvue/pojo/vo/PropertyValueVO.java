package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.PropertyValueBO;
import com.example.springvue.util.DateUtil;

/**
 * @Description: 属性值
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/22 20:05
 */
public class PropertyValueVO {
    /**
     * 商品属性值id
     */
    private Integer id;

    /**
     * 属性值
     */
    private String value;

    /**
     * 创建人名
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后修改人名
     */
    private String lastModifierName;

    /**
     * 最后修改时间
     */
    private String lastModifyTime;

    public void setBO(PropertyValueBO propertyValueBO) {
        this.id = propertyValueBO.getId();
        this.value = propertyValueBO.getValue();
        this.creatorName = propertyValueBO.getCreatorName();
        this.createTime = DateUtil.createTimeVO(propertyValueBO.getCreateTime());
        this.lastModifierName = propertyValueBO.getLastModifierName();
        this.lastModifyTime = DateUtil.createTimeVO(propertyValueBO.getLastModifyTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
