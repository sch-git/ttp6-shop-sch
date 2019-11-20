package com.example.springvue.pojo.dto;

import com.example.springvue.pojo.ao.GoodImageAO;

import java.util.Date;

/**
 * @Description: 商品图片
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 08:55
 */
public class GoodImageDTO {

    /**
     * 商品id
     */
    private Long goodId;

    /**
     * 图片类型
     */
    private Integer type;

    /**
     * 图片url
     */
    private String url;

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

    public GoodImageDTO() {

    }

    public void setAO(GoodImageAO goodImageAO) {
        this.goodId = goodImageAO.getGoodId();
        this.type = goodImageAO.getType();
        this.url = goodImageAO.getUrl();
        this.createBy = goodImageAO.getCreateBy();
        this.creatorName = goodImageAO.getCreatorName();
    }

    public Long getGoodId() {
        return goodId;
    }

    public void setGoodId(Long goodId) {
        this.goodId = goodId;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
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
}
