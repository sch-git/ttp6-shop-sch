package com.example.springvue.pojo.bo;

/**
 * @Description: 前台-首页分类商品数据
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/24 15:57
 */
public class UserGoodBO {
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
     * 商品描述
     */
    private String description;

    /**
     * 图片url
     */
    private String url;

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
