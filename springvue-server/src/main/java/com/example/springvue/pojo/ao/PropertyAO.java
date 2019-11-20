package com.example.springvue.pojo.ao;

/**
 * @Description: 属性
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/20 11:17
 */
public class PropertyAO {
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
     * 最后修改人id
     */
    private Long lastModifyBy;
    /**
     * 最后修改人名
     */
    private String lastModifierName;

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
}
