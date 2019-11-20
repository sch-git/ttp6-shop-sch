package com.example.springvue.pojo.vo;

import lombok.Data;

/**
 * @Description: 管理员
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/18 23:15
 */
@Data
public class AdminVO {
    /**
     * 管理员id
     */
    private Long id;

    /**
     * 管理员名称
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
