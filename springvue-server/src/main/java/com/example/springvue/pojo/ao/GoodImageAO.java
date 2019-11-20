package com.example.springvue.pojo.ao;

/**
 * @Description: 商品图片
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 08:52
 */
public class GoodImageAO {

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
}
