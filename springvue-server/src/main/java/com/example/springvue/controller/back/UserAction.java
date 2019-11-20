package com.example.springvue.controller.back;

import com.example.springvue.pojo.ao.UserAO;
import com.example.springvue.pojo.dto.UserDTO;
import com.example.springvue.pojo.vo.PointVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.pojo.vo.ResultCode;
import com.example.springvue.pojo.vo.UserVO;
import com.example.springvue.service.PointService;
import com.example.springvue.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 后台-用户
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 16:21
 */
@Api(tags = "后台用户")
@RestController
@RequestMapping("/lucky/admin")
public class UserAction {
    @Autowired
    private UserService userService;
    @Autowired
    private PointService pointService;

    /**
     * 查询所有用户
     *
     * @return
     */
    @GetMapping("/userList")
    public Result getUserList() {
        List<UserVO> userVOList = userService.getUserList();
        return Result.success(userVOList);
    }

    /**
     * 修改用户状态
     *
     * @param userAO 用户信息
     * @return
     */
    @PostMapping("/userStatus")
    public Result updateUserStatus(@RequestBody UserAO userAO) {
        UserDTO userDTO = new UserDTO(userAO);
        userService.updateUserStatus(userDTO);
        return Result.success();
    }

    /**
     * 动态查询用户
     *
     * @param userAO 查询信息
     * @return
     */
    @PostMapping("/userList")
    public Result getUserListByParams(@RequestBody UserAO userAO) {

        UserDTO userDTO = new UserDTO(userAO);
        List<UserVO> userVOList = userService.getUserByParams(userDTO);
        if (userVOList != null) {
            return Result.success(userVOList);
        } else {
            return Result.fail(ResultCode.USER_NOT_EXIST);
        }
    }

    /**
     * 查询用户积分历史记录
     *
     * @param id 用户id
     * @return 用户积分历史记录
     */
    @PostMapping("/point")
    public Result getUserPointHistory(@RequestParam Long id) {
        List<PointVO> pointVOList = pointService.getUserPoint(id);
        return Result.success(pointVOList);
    }
}
