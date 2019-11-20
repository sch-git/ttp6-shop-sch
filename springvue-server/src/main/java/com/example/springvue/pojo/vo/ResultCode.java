package com.example.springvue.pojo.vo;

/**
 * @Description: 返回状态码
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 13:30
 */
public enum ResultCode {
    /**
     * 成功状态码
     */
    SUCCESS(200, "成功"),
    /**
     * 不知道返回什么的失败状态码
     */
    FAIL(100, "失败"),
    /**
     * 用户错误
     */
    USER_NOT_EXIST(400, "用户不存在"),
    USER_HAS_EXIST(401, "用户已存在"),
    USER_LOGIN_ERROR(402, "密码错误"),
    USER_NOT_REGISTER(403, "用户未注册"),
    USER_STATUS_DISABLE(404, "用户已被禁用"),
    /**
     * 管理员错误
     */
    ADMIN_LOGIN_ERROR(403, "用户不存在或密码错误"),
    /**
     * 出现异常
     */
    SYSTEM_ERROR(500, "出现异常"),
    UPLOAD_ERROR(600, "上传出现错误");


    /**
     * 变量
     */
    private Integer code;
    private String message;

    /**
     * 方法
     *
     * @param code    状态码
     * @param message 提示信息
     */
    ResultCode(Integer code, String message) {
        this.code = code;
        this.message = message;
    }

    public Integer getCode() {
        return this.code;
    }

    public String getMessage() {
        return this.message;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
