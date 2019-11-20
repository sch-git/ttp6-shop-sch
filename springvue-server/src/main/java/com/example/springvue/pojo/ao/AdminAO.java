package com.example.springvue.pojo.ao;

/**
 * @Description: 管理员信息
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 15:02
 */
public class AdminAO {
    /**
     * 管理员名称
     */
    private String name;

    /**
     * 管理员密码
     */
    private String password;

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
