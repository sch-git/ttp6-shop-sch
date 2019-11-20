package com.example.springvue.dao;

import com.example.springvue.pojo.bo.OrderItemBO;
import com.example.springvue.pojo.dto.OrderItemDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:57
 */
@Repository
public interface OrderItemMapper {

    /**
     * 增加订单项
     *
     * @param orderItemDTOList 订单项数据
     */
    void insertOrderItemList(List<OrderItemDTO> orderItemDTOList);

    /**
     * 前台查询订单详情
     *
     * @param orderId 订单id
     * @return
     */
    List<OrderItemBO> getOrderItemList(Long orderId);
}