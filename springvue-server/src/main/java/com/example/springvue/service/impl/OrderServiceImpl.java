package com.example.springvue.service.impl;

import com.example.springvue.dao.OrderItemMapper;
import com.example.springvue.dao.OrderMapper;
import com.example.springvue.pojo.bo.OrderAdminBO;
import com.example.springvue.pojo.bo.OrderBO;
import com.example.springvue.pojo.bo.OrderItemBO;
import com.example.springvue.pojo.dto.CancelOrderDTO;
import com.example.springvue.pojo.dto.OrderDTO;
import com.example.springvue.pojo.dto.OrderItemDTO;
import com.example.springvue.pojo.dto.SuccessOrderDTO;
import com.example.springvue.pojo.vo.OrderAdminVO;
import com.example.springvue.pojo.vo.OrderItemVO;
import com.example.springvue.pojo.vo.OrderVO;
import com.example.springvue.pojo.vo.UserVO;
import com.example.springvue.service.OrderService;
import com.example.springvue.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * @Description: 订单管理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/27 15:03
 */
@Service
public class OrderServiceImpl implements OrderService {
    @Autowired
    private HttpSession session;
    @Autowired
    private OrderMapper orderMapper;
    @Autowired
    private OrderItemMapper orderItemMapper;

    /**
     * 创建订单
     *
     * @param orderDTO 订单数据
     * @return 订单id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public Long createOrder(OrderDTO orderDTO) {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        Byte orderStatus = 1;
        orderDTO.setUserId(userVO.getId());
        orderDTO.setStatus(orderStatus);
        orderDTO.setOrderNo(UUID.randomUUID().toString());
        orderDTO.setCreateDate(DateUtil.createTime());

        orderMapper.insertOrder(orderDTO);
        Long orderId = orderDTO.getId();
        return orderId;
    }

    /**
     * 增加订单项
     *
     * @param orderItemDTOList 订单项数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addOrderItemList(List<OrderItemDTO> orderItemDTOList) {
        orderItemMapper.insertOrderItemList(orderItemDTOList);
        return;
    }

    /**
     * 前台根据用户id查询订单
     *
     * @return 订单列表
     */
    @Override
    public List<OrderVO> getOrderListById() {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        List<OrderVO> orderVOList = new ArrayList<>();
        List<OrderBO> orderBOList = orderMapper.getOrderListByUserId(userVO.getId());
        for (OrderBO orderBO : orderBOList) {
            OrderVO orderVO = new OrderVO();
            orderVO.setBO(orderBO);
            orderVOList.add(orderVO);
        }
        return orderVOList;
    }

    /**
     * 前台根据订单id获取订单详情
     *
     * @param id 订单id
     * @return 订单详情
     */
    @Override
    public List<OrderItemVO> getOrderItemList(Long id) {
        List<OrderItemBO> orderItemBOList = orderItemMapper.getOrderItemList(id);
        List<OrderItemVO> orderItemVOList = new ArrayList<>();
        for (OrderItemBO orderItemBO : orderItemBOList) {
            OrderItemVO orderItemVO = new OrderItemVO();
            orderItemVO.setBO(orderItemBO);
            orderItemVOList.add(orderItemVO);
        }
        return orderItemVOList;
    }

    /**
     * 后台查询订单
     *
     * @return 订单列表
     */
    @Override
    public List<OrderAdminVO> getOrderList() {
        List<OrderAdminBO> orderAdminBOList = orderMapper.getOrderList();
        List<OrderAdminVO> orderAdminVOList = new ArrayList<>();
        for (OrderAdminBO orderAdminBO : orderAdminBOList) {
            OrderAdminVO orderAdminVO = new OrderAdminVO();
            orderAdminVO.setBO(orderAdminBO);
            orderAdminVOList.add(orderAdminVO);
        }
        return orderAdminVOList;
    }

    /**
     * 后台根据订单状态查询订单
     *
     * @param status 订单状态
     * @return 订单列表
     */
    @Override
    public List<OrderAdminVO> getOrderListByStatus(Byte status) {
        List<OrderAdminBO> orderAdminBOList = orderMapper.getOrderListByStatus(status);
        List<OrderAdminVO> orderAdminVOList = new ArrayList<>();
        for (OrderAdminBO orderAdminBO : orderAdminBOList) {
            OrderAdminVO orderAdminVO = new OrderAdminVO();
            orderAdminVO.setBO(orderAdminBO);
            orderAdminVOList.add(orderAdminVO);
        }
        return orderAdminVOList;
    }

    /**
     * 取消订单
     *
     * @param id 订单id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(Long id) {
        CancelOrderDTO cancelOrderDTO = new CancelOrderDTO();
        Byte status = new Byte("0");
        cancelOrderDTO.setId(id);
        cancelOrderDTO.setStatus(status);
        cancelOrderDTO.setDeleteTime(DateUtil.createTime());
        orderMapper.updateOrderStatusById(cancelOrderDTO);
    }

    /**
     * 完成订单
     *
     * @param id 订单id
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void successOrder(Long id) {
        SuccessOrderDTO successOrderDTO = new SuccessOrderDTO();
        Byte status = new Byte("2");
        successOrderDTO.setId(id);
        successOrderDTO.setStatus(status);
        successOrderDTO.setPayDate(DateUtil.createTime());
        // 完成订单
        orderMapper.updateOrderPayDateById(successOrderDTO);
    }

}
