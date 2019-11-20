package com.example.springvue.pojo.ao;

/**
 * @Description: 分类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/19 12:23
 */
public class CategoryAO {
    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类名称
     */
    private String name;

    /**
     * 分类状态
     */
    private Boolean status;

    /**
     * 父分类id
     */
    private Integer parentCategoryId;

    /**
     * 创建人id
     */
    private Long createBy;

    /**
     * 创建人名称
     */
    private String creatorName;

    /**
     * 最后修改人id
     */
    private Long lastModifyBy;

    /**
     * 最后修改人名称
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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public Integer getParentCategoryId() {
        return parentCategoryId;
    }

    public void setParentCategoryId(Integer parentCategoryId) {
        this.parentCategoryId = parentCategoryId;
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
