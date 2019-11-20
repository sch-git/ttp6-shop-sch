package com.example.springvue.pojo.bo;

import java.util.Date;

/**
 * @Description: 属性
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/20 14:54
 */
public class PropertyBO {
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
    private Date createTime;

    /**
     * 最后修改人名
     */
    private String lastModifierName;
    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

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

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
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
