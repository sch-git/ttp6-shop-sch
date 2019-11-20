package com.example.springvue.controller.front;

import com.example.springvue.pojo.ao.RegisterAO;
import com.example.springvue.pojo.ao.UserInfoAO;
import com.example.springvue.pojo.dto.RegisterDTO;
import com.example.springvue.pojo.dto.UserInfoDTO;
import com.example.springvue.pojo.vo.ReceiverVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.pojo.vo.UserInfoVO;
import com.example.springvue.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 前台-用户
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 22:03
 */
@Api(tags = "前台-用户")
@RestController
@RequestMapping("/lucky")
public class UserController {
    @Autowired
    private UserService userService;

    /**
     * 用户登录
     *
     * @param registerAO 登录信息
     * @return
     */
    @PostMapping("/login")
    public Result login(@RequestBody RegisterAO registerAO) {
        RegisterDTO registerDTO = new RegisterDTO(registerAO);
        return Result.http(userService.userLogin(registerDTO));
    }

    /**
     * 用户注册
     *
     * @param registerAO 注册信息
     * @return result
     */
    @PostMapping("/register")
    public Result register(@RequestBody RegisterAO registerAO) {
        RegisterDTO registerDTO = new RegisterDTO(registerAO);
        return Result.http(userService.userRegister(registerDTO));
    }

    /**
     * 获取收货人信息
     *
     * @return
     */
    @GetMapping("/receiver")
    public Result getReceiver() {
        ReceiverVO receiverVO = userService.getReceiver();
        return Result.success(receiverVO);
    }

    /**
     * 前台获取用户信息
     *
     * @return 用户信息
     */
    @GetMapping("/user/info")
    public Result getUserInfo() {
        UserInfoVO userInfoVO = userService.queryUserInfo();
        if (userInfoVO != null) {
            return Result.success(userInfoVO);
        } else {
            return Result.fail();
        }
    }

    /**
     * 前台-用户修改信息
     *
     * @param userInfoAO 修改数据
     * @return
     */
    @PutMapping("/user/info")
    public Result changeUserInfo(@RequestBody UserInfoAO userInfoAO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setAO(userInfoAO);
        userService.changeUserInfo(userInfoDTO);
        return Result.success();
    }

    /**
     * 前台-用户修改头像
     *
     * @param userInfoAO 用户头像url
     * @return
     */
    @PostMapping("/headImageUrl")
    public Result changeUserHeadImage(@RequestBody UserInfoAO userInfoAO) {
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        userInfoDTO.setHeadImageUrl(userInfoAO.getHeadImageUrl());
        userService.changeUserHeadImage(userInfoDTO);
        return Result.success();
    }
}
