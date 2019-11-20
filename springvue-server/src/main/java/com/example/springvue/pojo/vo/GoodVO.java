package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.GoodBO;
import com.example.springvue.util.DateUtil;

/**
 * @Description: 返回给前端的商品数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/21 09:44
 */
public class GoodVO {
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
    private String createTime;

    /**
     * 最后修改人名
     */
    private String lastModifierName;

    /**
     * 最后修改时间
     */
    private String lastModifyTime;

    /**
     * 商品描述
     */
    private String description;

    public GoodVO() {

    }

    public GoodVO(GoodBO goodBO) {
        this.id = goodBO.getId();
        this.name = goodBO.getName();
        this.stock = goodBO.getStock();
        this.price = goodBO.getPrice();
        this.status = goodBO.getStatus();
        this.creatorName = goodBO.getCreatorName();
        this.createTime = DateUtil.createTimeVO(goodBO.getCreateTime());
        this.lastModifierName = goodBO.getLastModifierName();
        this.lastModifyTime = DateUtil.createTimeVO(goodBO.getLastModifyTime());
        this.description = goodBO.getDescription();
    }

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

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLastModifierName() {
        return lastModifierName;
    }

    public void setLastModifierName(String lastModifierName) {
        this.lastModifierName = lastModifierName;
    }

    public String getLastModifyTime() {
        return lastModifyTime;
    }

    public void setLastModifyTime(String lastModifyTime) {
        this.lastModifyTime = lastModifyTime;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
