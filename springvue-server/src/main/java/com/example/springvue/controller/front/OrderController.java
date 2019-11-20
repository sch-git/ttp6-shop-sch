package com.example.springvue.controller.front;

import com.example.springvue.pojo.ao.OrderAO;
import com.example.springvue.pojo.ao.OrderItemAO;
import com.example.springvue.pojo.dto.OrderDTO;
import com.example.springvue.pojo.dto.OrderItemDTO;
import com.example.springvue.pojo.vo.OrderItemVO;
import com.example.springvue.pojo.vo.OrderVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.service.*;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 前台-订单
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 22:25
 */
@Api(tags = "前台-订单")
@RestController
@RequestMapping("/lucky/order")
public class OrderController {
    @Autowired
    private OrderService orderService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private PointService pointService;
    @Autowired
    private UserService userService;

    /**
     * 前台获取订单
     *
     * @return
     */
    @GetMapping("/list")
    public Result getOrderListById() {
        List<OrderVO> orderVOList = orderService.getOrderListById();
        return Result.success(orderVOList);
    }

    /**
     * 前台获取订单详情
     *
     * @param id 订单id
     * @return
     */
    @GetMapping("/item")
    public Result getOrderItemList(@RequestParam(value = "orderId") Long id) {
        List<OrderItemVO> orderItemVOList = orderService.getOrderItemList(id);
        return Result.success(orderItemVOList);
    }

    /**
     * 取消订单
     *
     * @param orderItemAOList 订单项数据
     * @return
     */
    @PutMapping("/order")
    public Result cancelOrder(@RequestBody List<OrderItemAO> orderItemAOList) {
        // 取消订单
        orderService.cancelOrder(orderItemAOList.get(0).getOrderId());
        // 恢复商品库存
        for (OrderItemAO orderItemAO : orderItemAOList) {
            goodService.restoreGoodStock(orderItemAO.getGoodId(), orderItemAO.getNumber());
        }
        return Result.success();
    }

    /**
     * 完成订单
     *
     * @param id 订单id
     * @return
     */
    @PutMapping("/success")
    public Result successOrder(@RequestParam(value = "id") Long id, @RequestParam(value = "score") Integer score) {
        orderService.successOrder(id);
        // 积分变动
        pointService.addPoint(score);
        // 更新用户总积分
        userService.addTotalScore(score);
        return Result.success();
    }

    /**
     * 创建订单
     *
     * @param orderAO 订单所需前台数据
     * @return 订单id
     */
    @PostMapping("/order")
    public Result createOrder(@RequestBody OrderAO orderAO) {
        OrderDTO orderDTO = new OrderDTO();
        orderDTO.setAO(orderAO);

        Long orderId = orderService.createOrder(orderDTO);
        return Result.success(orderId);
    }

    /**
     * 生成订单项
     *
     * @param orderItemAOList 订单项
     * @return
     */
    @PostMapping("/item")
    public Result addOrderItem(@RequestBody List<OrderItemAO> orderItemAOList) {
        List<OrderItemDTO> orderItemDTOList = new ArrayList<>();
        for (OrderItemAO orderItemAO : orderItemAOList) {
            OrderItemDTO orderItemDTO = new OrderItemDTO();
            orderItemDTO.setAO(orderItemAO);
            orderItemDTOList.add(orderItemDTO);
        }
        orderService.addOrderItemList(orderItemDTOList);
        // 从购物车中删除订单商品并更新商品库存
        for (OrderItemAO orderItemAO : orderItemAOList) {
            shopCarService.deleteCarItem(orderItemAO.getGoodId());
            goodService.updateGoodStock(orderItemAO.getGoodId(), orderItemAO.getNumber());
        }
        return Result.success();
    }

}
