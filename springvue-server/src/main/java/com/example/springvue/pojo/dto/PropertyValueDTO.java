package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.PropertyValueAO;

import java.util.Date;

/**
 * @Description: 属性值
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/22 00:58
 */
public class PropertyValueDTO {
    /**
     * 商品属性值id
     */
    private Long id;

    /**
     * 属性id
     */
    private Long propertyId;

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 属性值
     */
    private String value;

    /**
     * 创建人id
     */
    private Long createBy;

    /**
     * 创建人名
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人id
     */
    private Long lastModifyBy;

    /**
     * 最后修改人名
     */
    private String lastModifierName;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    public PropertyValueDTO() {

    }

    public void setAO(PropertyValueAO propertyValueAO) {
        this.id = propertyValueAO.getId();
        this.propertyId = propertyValueAO.getPropertyId();
        this.goodId = propertyValueAO.getGoodId();
        this.value = propertyValueAO.getValue();
        this.createBy = propertyValueAO.getCreateBy();
        this.creatorName = propertyValueAO.getCreatorName();
        this.lastModifyBy = propertyValueAO.getLastModifyBy();
        this.lastModifierName = propertyValueAO.getLastModifierName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Long propertyId) {
        this.propertyId = propertyId;
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Long getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(Long lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }
}
