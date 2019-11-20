package com.example.springvue.pojo.ao;

/**
 * @Description: 修改购物车商品数量
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/26 15:58
 */
public class UpdateCarItemAO {
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品数量
     */
    private Integer number;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }
}
