package com.example.springvue.service;

import com.example.springvue.pojo.dto.OrderDTO;
import com.example.springvue.pojo.dto.OrderItemDTO;
import com.example.springvue.pojo.vo.OrderAdminVO;
import com.example.springvue.pojo.vo.OrderItemVO;
import com.example.springvue.pojo.vo.OrderVO;

import java.util.List;

/**
 * @Description: 订单管理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/27 14:36
 */
public interface OrderService {
    /**
     * 创建订单
     *
     * @param orderDTO 订单数据
     * @return 订单id
     */
    Long createOrder(OrderDTO orderDTO);

    /**
     * 增加订单项
     *
     * @param orderItemDTOList 订单项数据
     */
    void addOrderItemList(List<OrderItemDTO> orderItemDTOList);

    /**
     * 前台根据用户id查询订单
     *
     * @return
     */
    List<OrderVO> getOrderListById();

    /**
     * 前台根据订单id获取订单详情
     *
     * @param id 订单id
     * @return
     */
    List<OrderItemVO> getOrderItemList(Long id);

    /**
     * 后台查询订单
     *
     * @return
     */
    List<OrderAdminVO> getOrderList();

    /**
     * 后台根据订单状态查询订单
     *
     * @param status 订单状态
     * @return
     */
    List<OrderAdminVO> getOrderListByStatus(Byte status);

    /**
     * 取消订单
     *
     * @param id 订单id
     */
    void cancelOrder(Long id);

    /**
     * 完成订单
     *
     * @param id 订单id
     */
    void successOrder(Long id);
}
