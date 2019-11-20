package com.example.springvue.service;

import com.example.springvue.pojo.dto.RegisterDTO;
import com.example.springvue.pojo.dto.UserDTO;
import com.example.springvue.pojo.dto.UserInfoDTO;
import com.example.springvue.pojo.vo.ReceiverVO;
import com.example.springvue.pojo.vo.ResultCode;
import com.example.springvue.pojo.vo.UserInfoVO;
import com.example.springvue.pojo.vo.UserVO;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/7 11:30
 */
public interface UserService {

    /**
     * 根据手机查询用户
     *
     * @param phone
     * @return: UserVO
     */
    UserVO selectUserByPhone(String phone);

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return: 短信验证码
     */
    void sendSms(String phone);

    /**
     * 用户注册
     *
     * @param registerDTO
     * @return
     */
    ResultCode userRegister(RegisterDTO registerDTO);

    /**
     * 用户登录
     *
     * @param registerDTO
     * @return
     */
    ResultCode userLogin(RegisterDTO registerDTO);

    /**
     * 前台-用户修改数据
     *
     * @param userInfoDTO 修改数据
     */
    void changeUserInfo(UserInfoDTO userInfoDTO);

    /**
     * 前台-用户修改头像
     *
     * @param userInfoDTO
     */
    void changeUserHeadImage(UserInfoDTO userInfoDTO);

    /**
     * 前台-查询用户信息
     *
     * @return
     */
    UserInfoVO queryUserInfo();

    /**
     * 获取收货人信息
     *
     * @return 收货人信息
     */
    ReceiverVO getReceiver();

    /**
     * 后台查询所有用户
     *
     * @return
     */
    List<UserVO> getUserList();

    /**
     * 修改用户状态
     *
     * @param userDTO
     * @return
     */
    void updateUserStatus(UserDTO userDTO);

    /**
     * 动态查询用户
     *
     * @param userDTO 查询信息
     * @return
     */
    List<UserVO> getUserByParams(UserDTO userDTO);

    /**
     * 更新用户总积分
     *
     * @param score 积分增量
     */
    void addTotalScore(Integer score);
}
