package com.example.springvue.dao;

import com.example.springvue.pojo.dto.GoodImageDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:45
 */
@Repository
public interface GoodImageMapper {

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
     * @param goodImageDTOS 商品图片数据
     * @return
     */
    Integer insertGoodImageList(List<GoodImageDTO> goodImageDTOS);

    /**
     * 前台-查询商品图片
     *
     * @param goodId 商品id
     * @return 商品图片
     */
    List<String> getGoodInfoUrlListByGoodId(Long goodId);
}