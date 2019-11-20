package com.example.springvue.service;

import com.example.springvue.pojo.dto.GoodImageDTO;

import java.util.List;

/**
 * @Description: 商品图片
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 00:03
 */
public interface GoodImageService {
    /**
     * 根据商品id获取商品图片
     *
     * @param goodId
     * @return
     */
    List<String> getImageUrlByGoodId(Long goodId);

    /**
     * 保存商品图片url
     *
     * @param goodImageDTOS 图片数据
     */
    void addGoodImageList(List<GoodImageDTO> goodImageDTOS);
}
