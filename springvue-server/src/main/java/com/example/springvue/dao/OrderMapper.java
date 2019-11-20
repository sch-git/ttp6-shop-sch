package com.example.springvue.dao;

import com.example.springvue.pojo.bo.OrderAdminBO;
import com.example.springvue.pojo.bo.OrderBO;
import com.example.springvue.pojo.dto.CancelOrderDTO;
import com.example.springvue.pojo.dto.OrderDTO;
import com.example.springvue.pojo.dto.SuccessOrderDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:40
 */
@Repository
public interface OrderMapper {

    /**
     * 创建订单
     *
     * @param orderDTO 订单数据
     * @return 订单id
     */
    Integer insertOrder(OrderDTO orderDTO);

    /**
     * 前台根据用户id查询订单
     *
     * @param userId 用户id
     * @return 用户订单信息
     */
    List<OrderBO> getOrderListByUserId(Long userId);

    /**
     * 后台查询订单
     *
     * @return
     */
    List<OrderAdminBO> getOrderList();

    /**
     * 后台根据订单状态查询订单
     *
     * @param status
     * @return
     */
    List<OrderAdminBO> getOrderListByStatus(@Param(value = "status") Byte status);

    /**
     * 取消订单
     *
     * @param cancelOrderDTO 取消信息
     */
    void updateOrderStatusById(CancelOrderDTO cancelOrderDTO);

    /**
     * 完成订单
     *
     * @param successOrderDTO 完成信息
     */
    void updateOrderPayDateById(SuccessOrderDTO successOrderDTO);
}