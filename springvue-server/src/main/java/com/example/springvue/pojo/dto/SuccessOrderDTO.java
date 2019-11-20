package com.example.springvue.pojo.dto;

import java.util.Date;

/**
 * @Description: 完成支付
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/10/10 09:25
 */
public class SuccessOrderDTO {
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
    private Date payDate;

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

    public Date getPayDate() {
        return payDate;
    }

    public void setPayDate(Date payDate) {
        this.payDate = payDate;
    }
}
