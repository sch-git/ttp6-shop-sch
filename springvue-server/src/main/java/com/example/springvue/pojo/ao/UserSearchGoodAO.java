package com.example.springvue.pojo.ao;

/**
 * @Description: 用户查询商品的查询数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/24 17:22
 */

public class UserSearchGoodAO {
    /**
     * 分类id
     */
    private Long id;

    /**
     * 商品名
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
