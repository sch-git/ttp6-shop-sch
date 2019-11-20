package com.example.springvue.controller.front;

import com.example.springvue.pojo.ao.UserSearchGoodAO;
import com.example.springvue.pojo.bo.UserCategoryBO;
import com.example.springvue.pojo.bo.UserGoodBO;
import com.example.springvue.pojo.dto.UserSearchGoodDTO;
import com.example.springvue.pojo.vo.GoodInfoVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.service.GoodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 前台-商品
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 22:17
 */
@Api(tags = "前台-商品")
@RestController
@RequestMapping("/lucky/good")
public class GoodController {
    @Autowired
    private GoodService goodService;

    /**
     * 根据id获取商品基本信息
     *
     * @param id 商品id
     * @return 商品基本信息
     */
    @GetMapping("/info")
    public Result GoodInfo(@RequestParam(value = "id") Long id) {
        GoodInfoVO goodInfoVO = goodService.getGoodInfo(id);
        return Result.success(goodInfoVO);
    }

    /**
     * 按分类展示商品
     *
     * @return
     */
    @GetMapping("/list")
    public Result getUserGoodList() {
        List<UserCategoryBO> userCategoryBOS = goodService.getUserGoodListByCategoryId();
        return Result.success(userCategoryBOS);
    }

    /**
     * 用户查询商品
     *
     * @param userSearchGoodAO 查询数据
     * @return 商品数据
     */
    @PostMapping("/list")
    public Result UserSearchGood(@RequestBody UserSearchGoodAO userSearchGoodAO) {
        UserSearchGoodDTO userSearchGoodDTO = new UserSearchGoodDTO();
        userSearchGoodDTO.setAO(userSearchGoodAO);
        List<UserGoodBO> goodList = goodService.getUserGoodList(userSearchGoodDTO);
        return Result.success(goodList);
    }
}
