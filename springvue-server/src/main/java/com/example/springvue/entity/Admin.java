package com.example.springvue.entity;

/**
 * @Description: 管理员
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:25
 */
public class Admin {
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