package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.RegisterAO;

import java.util.Date;

/**
 * @Description: 注册
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 22:27
 */
public class RegisterDTO {
    /**
     * 密码
     */
    private String password;

    /**
     * 手机号
     */
    private String phone;

    /**
     * 创建日期
     */
    private Date createDate;

    /**
     * 用户名/昵称
     */
    private String name;

    /**
     * 密码盐
     */
    private Byte salt;

    public RegisterDTO() {

    }

    public RegisterDTO(RegisterAO registerAO) {
        this.phone = registerAO.getPhone();
        this.password = registerAO.getPassword();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Byte getSalt() {
        return salt;
    }

    public void setSalt(Byte salt) {
        this.salt = salt;
    }
}
