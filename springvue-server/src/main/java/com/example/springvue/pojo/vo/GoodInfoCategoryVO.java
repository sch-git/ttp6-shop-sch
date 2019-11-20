package com.example.springvue.pojo.vo;

/**
 * @Description: 前台-商品分类信息
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/26 09:09
 */
public class GoodInfoCategoryVO {
    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;

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
}
