package com.example.springvue.pojo.bo;

import java.util.List;

/**
 * @Description: 前台-首页拥有商品的分类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/24 16:09
 */
public class UserCategoryBO {
    /**
     * 分类id
     */
    private Long id;

    /**
     * 分类名
     */
    private String name;
    /**
     * 分类下的商品
     */
    private List<UserGoodBO> goodList;

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

    public List<UserGoodBO> getGoodList() {
        return goodList;
    }

    public void setGoodList(List<UserGoodBO> goodList) {
        this.goodList = goodList;
    }
}
