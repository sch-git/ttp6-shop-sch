package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.GoodAO;

import java.util.Date;

/**
 * @Description: 后台处理的商品数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/21 09:41
 */
public class GoodDTO {
    /**
     * 商品id
     */
    private Long id;

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
     * 创建人id
     */
    private Long createBy;

    /**
     * 创建人名
     */
    private String creatorName;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改人id
     */
    private Long lastModifyBy;

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

    public GoodDTO() {

    }

    public GoodDTO(GoodAO goodAO) {
        this.id = goodAO.getId();
        this.name = goodAO.getName();
        this.stock = goodAO.getStock();
        this.price = goodAO.getPrice();
        this.status = goodAO.getStatus();
        this.createBy = goodAO.getCreateBy();
        this.creatorName = goodAO.getCreatorName();
        this.lastModifyBy = goodAO.getLastModifyBy();
        this.lastModifierName = goodAO.getLastModifierName();
        this.description = goodAO.getDescription();
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
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

    public Long getCreateBy() {
        return createBy;
    }

    public void setCreateBy(Long createBy) {
        this.createBy = createBy;
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

    public Long getLastModifyBy() {
        return lastModifyBy;
    }

    public void setLastModifyBy(Long lastModifyBy) {
        this.lastModifyBy = lastModifyBy;
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
