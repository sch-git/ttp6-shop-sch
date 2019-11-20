package com.example.springvue.controller.front;

import com.example.springvue.pojo.ao.CarItemAO;
import com.example.springvue.pojo.ao.UpdateCarItemAO;
import com.example.springvue.pojo.dto.CarItemDTO;
import com.example.springvue.pojo.vo.CarItemVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.service.ShopCarService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: 购物车
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 22:12
 */
@Api(tags = "购物车")
@RestController
@RequestMapping("/lucky/car")
public class ShopCarController {
    @Autowired
    private ShopCarService shopCarService;
    @Autowired
    private HttpSession session;

    /**
     * 从购物车中删除商品
     *
     * @param id 商品id
     * @return
     */
    @DeleteMapping("/item")
    public Result deleteCarItem(@RequestParam(value = "id") Long id) {
        shopCarService.deleteCarItem(id);
        return Result.success();
    }

    /**
     * 修改购物车中商品数量
     *
     * @param updateCarItemAO 商品id,数量
     * @return
     */
    @PostMapping("/itemNumber")
    public Result updateCarItemNumber(@RequestBody UpdateCarItemAO updateCarItemAO) {
        shopCarService.updateCarItemNumber(updateCarItemAO.getId(), updateCarItemAO.getNumber());
        return Result.success();
    }

    /**
     * 从sesion中获取购物车数据
     *
     * @return 购物车中商品数据
     */
    @GetMapping("/item")
    public Result listCarItem() {
        List<CarItemVO> carItemVOList = (List<CarItemVO>) session.getAttribute(session.getId() + "car");
        return Result.success(carItemVOList);
    }

    /**
     * 添加商品到购物车
     *
     * @param carItemAO 购物车商品详情
     * @return
     */
    @PostMapping("/item")
    public Result addCarItem(@RequestBody CarItemAO carItemAO) {
        CarItemDTO carItemDTO = new CarItemDTO();
        carItemDTO.setAO(carItemAO);
        shopCarService.addCarItem(carItemDTO);
        return Result.success();
    }
}
