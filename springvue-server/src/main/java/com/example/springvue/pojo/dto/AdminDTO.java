package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.AdminAO;

/**
 * @Description: 管理员
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/18 23:21
 */
public class AdminDTO {
    /**
     * 管理员id
     */
    private Integer id;

    /**
     * 管理员名称
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;

    public void setAO(AdminAO adminAO) {
        this.name = adminAO.getName();
        this.password = adminAO.getPassword();
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

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
