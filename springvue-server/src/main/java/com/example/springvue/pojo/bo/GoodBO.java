package com.example.springvue.pojo.bo;

import java.util.Date;

/**
 * @Description: 数据库返回的商品数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/21 09:42
 */
public class GoodBO {
    /**
     * 商品id
     */
    private Integer id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 商品库存
     */
    private Integer stock;

    /**
     * 商品价格
     */
    private Long price;

    /**
     * 商品状态
     */
    private Boolean status;

    /**
     * 创建人名
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人名
     */
    private String lastModifierName;

    /**
     * 最后修改时间
     */
    private Date lastModifyTime;

    /**
     * 商品描述
     */
    private String description;

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

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getCreatorName() {
        return creatorName;
    }

    public void setCreatorName(String creatorName) {
        this.creatorName = creatorName;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    public Date getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(Date lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
