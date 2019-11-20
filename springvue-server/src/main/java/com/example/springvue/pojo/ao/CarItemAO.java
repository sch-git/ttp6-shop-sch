package com.example.springvue.pojo.ao;

/**
 * @Description: 购物车
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/26 13:44
 */
public class CarItemAO {
    /**
     * 商品id
     */
    private Long id;

    /**
     * 商品名
     */
    private String name;

    /**
     * 购买数量
     */
    private Integer number;

    /**
     * 价格
     */
    private Integer price;

    /**
     * 商品图片
     */
    private String url;

    /**
     * 商品库存
     */
    private Integer stock;

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

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public Integer getPrice() {
        return price;
    }

    public void setPrice(Integer price) {
        this.price = price;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }
}
