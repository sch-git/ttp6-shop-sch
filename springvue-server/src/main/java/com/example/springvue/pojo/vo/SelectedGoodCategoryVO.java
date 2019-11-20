package com.example.springvue.pojo.vo;

/**
 * @Description: 商品分类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/21 23:05
 */
public class SelectedGoodCategoryVO {
    /**
     * 分类id
     */
    private Integer categoryId;

    /**
     * 分类名
     */
    private String categoryName;

    public Integer getCategoryId() {
        return categoryId;
    }

    public void setCategoryId(Integer categoryId) {
        this.categoryId = categoryId;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }
}
