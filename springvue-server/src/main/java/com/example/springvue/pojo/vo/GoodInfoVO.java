package com.example.springvue.pojo.vo;

import com.example.springvue.pojo.bo.GoodInfoBO;

import java.util.List;

/**
 * @Description: 前台-商品详细数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/26 08:44
 */
public class GoodInfoVO {
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
     * 商品描述
     */
    private String description;

    /**
     * 商品图片url
     */
    private List<String> urlList;

    /**
     * 商品分类
     */
    private List<GoodInfoCategoryVO> categoryList;

    public void setBO(GoodInfoBO goodInfoBO) {
        this.id = goodInfoBO.getId();
        this.name = goodInfoBO.getName();
        this.stock = goodInfoBO.getStock();
        this.price = goodInfoBO.getPrice();
        this.description = goodInfoBO.getDescription();
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<String> getUrlList() {
        return urlList;
    }

    public void setUrlList(List<String> urlList) {
        this.urlList = urlList;
    }

    public List<GoodInfoCategoryVO> getCategoryList() {
        return categoryList;
    }

    public void setCategoryList(List<GoodInfoCategoryVO> categoryList) {
        this.categoryList = categoryList;
    }
}
