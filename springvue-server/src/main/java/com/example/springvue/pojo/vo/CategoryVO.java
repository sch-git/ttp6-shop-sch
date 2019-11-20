package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.CategoryBO;
import com.example.springvue.util.DateUtil;

/**
 * @Description: 分类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/19 12:25
 */
public class CategoryVO {
    /**
     * 分类id
     */
    private Integer id;

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
     * 创建人名称
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private String createTime;

    /**
     * 最后修改人名称
     */
    private String lastModifierName;

    /**
     * 最后修改时间
     */
    private String lastModifyTime;

    public CategoryVO() {

    }

    /**
     * 将bo中的时间转换
     *
     * @param categoryBO
     */
    public CategoryVO(CategoryBO categoryBO) {
        this.id = categoryBO.getId();
        this.name = categoryBO.getName();
        this.status = categoryBO.getStatus();
        this.parentCategoryId = categoryBO.getParentCategoryId();
        this.creatorName = categoryBO.getCreatorName();
        this.createTime = DateUtil.createTimeVO(categoryBO.getCreateTime());
        this.lastModifierName = categoryBO.getLastModifierName();
        this.lastModifyTime = DateUtil.createTimeVO(categoryBO.getLastModifyTime());
    }

    public final void setBO(CategoryBO categoryBO) {
        this.id = categoryBO.getId();
        this.name = categoryBO.getName();
        this.status = categoryBO.getStatus();
        this.parentCategoryId = categoryBO.getParentCategoryId();
        this.creatorName = categoryBO.getCreatorName();
        this.createTime = DateUtil.createTimeVO(categoryBO.getCreateTime());
        this.lastModifierName = categoryBO.getLastModifierName();
        this.lastModifyTime = DateUtil.createTimeVO(categoryBO.getLastModifyTime());
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
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
