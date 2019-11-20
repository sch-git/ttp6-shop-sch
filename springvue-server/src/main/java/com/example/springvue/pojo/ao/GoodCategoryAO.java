package com.example.springvue.pojo.ao;

/**
 * @Description: 商品分类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/21 16:58
 */
public class GoodCategoryAO {

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 商品名
     */
    private String goodName;

    /**
     * 分类id
     */
    private Long categoryId;

    /**
     * 分类名
     */
    private String categoryName;

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

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public String getGoodName() {
        return goodName;
    }

    public void setGoodName(String goodName) {
        this.goodName = goodName;
    }

    public Long getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Long categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
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
