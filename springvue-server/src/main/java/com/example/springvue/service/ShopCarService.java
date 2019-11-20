package com.example.springvue.service;

import com.example.springvue.pojo.dto.CarItemDTO;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/26 13:47
 */
public interface ShopCarService {
    /**
     * 添加商品到购物车
     *
     * @param carItemDTO
     */
    void addCarItem(CarItemDTO carItemDTO);

    /**
     * 从购物车中删除商品
     *
     * @param id 商品id
     */
    void deleteCarItem(Long id);

    /**
     * 修改购物车中商品数量
     *
     * @param id     商品id
     * @param number 商品数量
     */
    void updateCarItemNumber(Long id, Integer number);
}
