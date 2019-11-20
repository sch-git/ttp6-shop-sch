package com.example.springvue.pojo.vo;

import java.io.Serializable;

/**
 * @Description: 封装返回结果
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 13:43
 */
public class Result implements Serializable {
    /**
     * 状态码
     */
    private Integer code;
    /**
     * 返回信息
     */
    private String msg;
    /**
     * 数据
     */
    private Object data;

    /**
     * 返回成功-无数据
     *
     * @return
     */
    public static Result success() {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        return result;
    }

    /**
     * 返回成功-带数据
     *
     * @param data
     * @return
     */
    public static Result success(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.SUCCESS);
        result.setData(data);
        return result;
    }

    /**
     * 返回-不确定成功或失败
     *
     * @param resultCode 返回代码
     * @return 返回代码
     */
    public static Result http(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回失败-带消息
     *
     * @return
     */
    public static Result fail() {
        Result result = new Result();
        result.setResultCode(ResultCode.FAIL);
        return result;
    }

    /**
     * 返回失败-带消息
     *
     * @param resultCode 枚举对象
     * @return
     */
    public static Result fail(ResultCode resultCode) {
        Result result = new Result();
        result.setResultCode(resultCode);
        return result;
    }

    /**
     * 返回失败-带数据
     *
     * @param data 数据
     * @return
     */
    public static Result fail(Object data) {
        Result result = new Result();
        result.setResultCode(ResultCode.FAIL);
        result.setData(data);
        return result;
    }

    public final void setResultCode(ResultCode resultCode) {
        this.code = resultCode.getCode();
        this.msg = resultCode.getMessage();
    }

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
