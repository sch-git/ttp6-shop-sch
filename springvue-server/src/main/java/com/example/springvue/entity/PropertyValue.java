package com.example.springvue.entity;

import java.util.Date;

/**
 * @Description: 属性值
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:51
 */
public class PropertyValue {
    /**
     * 商品属性值id
     */
    private Integer id;

    /**
     * 属性id
     */
    private Integer propertyId;

    /**
     * 商品id
     */
    private Integer goodId;

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

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getPropertyId() {
        return propertyId;
    }

    public void setPropertyId(Integer propertyId) {
        this.propertyId = propertyId;
    }

    public Integer getGoodId() {
        return goodId;
    }

    public void setGoodId(Integer goodId) {
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