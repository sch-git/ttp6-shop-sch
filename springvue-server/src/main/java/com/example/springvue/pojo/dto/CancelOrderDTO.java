package com.example.springvue.pojo.dto;

import lombok.Data;

import java.util.Date;

/**
 * @Description: 取消支付
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/10/8 17:21
 */
@Data
public class CancelOrderDTO {
    /**
     * 订单id
     */
    private Long id;

    /**
     * 订单状态
     */
    private Byte status;

    /**
     * 取消订单时间
     */
    private Date deleteTime;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Date getDeleteTime() {
        return deleteTime;
    }

    public void setDeleteTime(Date deleteTime) {
        this.deleteTime = deleteTime;
    }
}
