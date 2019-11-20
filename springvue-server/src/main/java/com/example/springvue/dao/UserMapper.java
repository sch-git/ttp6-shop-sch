package com.example.springvue.dao;

import com.example.springvue.pojo.bo.UserBO;
import com.example.springvue.pojo.bo.UserInfoBO;
import com.example.springvue.pojo.dto.RegisterDTO;
import com.example.springvue.pojo.dto.UserDTO;
import com.example.springvue.pojo.dto.UserInfoDTO;
import com.example.springvue.pojo.vo.ReceiverVO;
import com.example.springvue.pojo.vo.UserVO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: 用户管理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/07 11:25
 */
@Repository
public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return: UserBO用户列表
     */
    List<UserBO> getUserList();

    /**
     * 根据手机号查询用户
     *
     * @param phone 手机号
     * @return 一个用户对象
     */
    UserVO getUserByPhone(String phone);

    /**
     * 用户注册，增加用户
     *
     * @param registerDTO 注册信息
     * @return
     */
    Integer insertUser(RegisterDTO registerDTO);

    /**
     * 用户登录，根据手机号查询密码盐
     *
     * @param phone 手机号
     * @return
     */
    String getSaltByPhone(String phone);

    /**
     * 用户登录，根据用户名密码查询用户
     *
     * @param registerDTO
     * @return
     */
    UserBO getUserByPhoneAndPassword(RegisterDTO registerDTO);

    /**
     * 后台管理员操作：根据用户id修改用户状态
     *
     * @param userDTO
     * @return
     */
    Integer updateUserStatusById(UserDTO userDTO);

    /**
     * 根据用户名和用户状态动态查询
     *
     * @param userDTO
     * @return
     */
    List<UserBO> getUserListByParams(UserDTO userDTO);

    /**
     * 用户修改信息
     *
     * @param userInfoDTO 修改数据
     * @return
     */
    Integer updateUserInfoByParams(UserInfoDTO userInfoDTO);

    /**
     * 用户修改头像
     *
     * @param userInfoDTO 头像数据
     * @return
     */
    Integer updateUserHeadImageUrl(UserInfoDTO userInfoDTO);


    /**
     * 前台查询用户信息
     *
     * @param id 用户id
     * @return
     */
    UserInfoBO getUserInfo(Long id);

    /**
     * 获取收货人信息
     *
     * @param id 用户id
     * @return 收货人信息
     */
    ReceiverVO getReceiver(Long id);

    /**
     * 更新用户总积分
     *
     * @param id         用户id
     * @param totalScore 积分增加量
     * @return
     */
    Integer updateTotalScore(@Param("id") Long id, @Param("totalScore") Integer totalScore);
}