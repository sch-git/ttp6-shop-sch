package com.example.springvue.controller.back;

import com.example.springvue.pojo.vo.OrderAdminVO;
import com.example.springvue.pojo.vo.OrderItemVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.service.OrderService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 后台-订单
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 21:49
 */
@Api(tags = "订单")
@RestController
@RequestMapping("/lucky/admin/order")
public class OrderAction {
    @Autowired
    private OrderService orderService;

    /**
     * 根据订单状态查询订单
     *
     * @param status 订单状态
     * @return
     */
    @PostMapping("/order")
    public Result getOrderListByStatus(@RequestParam(value = "status") Byte status) {
        List<OrderAdminVO> orderAdminVOList = orderService.getOrderListByStatus(status);
        return Result.success(orderAdminVOList);
    }

    /**
     * 查询订单
     *
     * @return
     */
    @GetMapping("/order")
    public Result getOrderList() {
        List<OrderAdminVO> orderAdminVOList = orderService.getOrderList();
        return Result.success(orderAdminVOList);
    }

    /**
     * 前台获取订单详情
     *
     * @param id 订单id
     * @return
     */
    @GetMapping("/orderItem")
    public Result getOrderItemList(@RequestParam(value = "orderId") Long id) {
        List<OrderItemVO> orderItemVOList = orderService.getOrderItemList(id);
        return Result.success(orderItemVOList);
    }
}
