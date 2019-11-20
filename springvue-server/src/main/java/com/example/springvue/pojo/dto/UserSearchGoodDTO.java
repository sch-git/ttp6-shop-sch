package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.UserSearchGoodAO;

/**
 * @Description: 用户查询商品的查询数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/24 17:33
 */
public class UserSearchGoodDTO {
    /**
     * 分类id
     */
    private Long id;

    /**
     * 商品名
     */
    private String name;

    public void setAO(UserSearchGoodAO userSearchGoodAO) {
        this.id = userSearchGoodAO.getId();
        this.name = userSearchGoodAO.getName();
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
}
