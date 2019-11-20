package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.PropertyBO;
import com.example.springvue.pojo.dto.PropertyDTO;
import com.example.springvue.util.DateUtil;

/**
 * @Description: 属性
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/20 11:21
 */
public class PropertyVO {
    /**
     * 属性id
     */
    private Long id;
    /**
     * 属性名
     */
    private String name;

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

    public PropertyVO() {

    }

    public PropertyVO(PropertyBO propertyBO) {
        this.id = propertyBO.getId();
        this.name = propertyBO.getName();
        this.creatorName = propertyBO.getCreatorName();
        this.createTime = DateUtil.createTimeVO(propertyBO.getCreateTime());
        this.lastModifierName = propertyBO.getLastModifierName();
        this.lastModifyTime = DateUtil.createTimeVO(propertyBO.getLastModifyTime());
    }

    public PropertyVO(PropertyDTO propertyDTO) {
        this.id = propertyDTO.getId();
        this.name = propertyDTO.getName();
        this.lastModifierName = propertyDTO.getLastModifierName();
        this.lastModifyTime = DateUtil.createTimeVO(propertyDTO.getLastModifyTime());
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
