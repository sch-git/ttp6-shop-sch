package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.PropertyAO;
import lombok.Data;

import java.util.Date;

/**
 * @Description: 属性
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/20 11:20
 */
@Data
public class PropertyDTO {
    /**
     * 属性id
     */
    private Long id;
    /**
     * 属性名
     */
    private String name;
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

    public PropertyDTO() {

    }

    public PropertyDTO(PropertyAO propertyAO) {
        this.id = propertyAO.getId();
        this.name = propertyAO.getName();
        this.createBy = propertyAO.getCreateBy();
        this.creatorName = propertyAO.getCreatorName();
        this.lastModifyBy = propertyAO.getLastModifyBy();
        this.lastModifierName = propertyAO.getLastModifierName();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
