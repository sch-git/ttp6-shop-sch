package com.example.springvue.service.impl;

import com.example.springvue.dao.UserMapper;
import com.example.springvue.pojo.bo.UserBO;
import com.example.springvue.pojo.bo.UserInfoBO;
import com.example.springvue.pojo.dto.RegisterDTO;
import com.example.springvue.pojo.dto.UserDTO;
import com.example.springvue.pojo.dto.UserInfoDTO;
import com.example.springvue.pojo.vo.*;
import com.example.springvue.service.UserService;
import com.example.springvue.util.DateUtil;
import com.example.springvue.util.Md5EncodeUtil;
import com.example.springvue.util.SmsUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/7 11:31
 */
@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private HttpServletRequest request;
    @Autowired
    private HttpSession session;

    /**
     * 根据手机查询用户
     *
     * @param phone 手机号
     * @return: UserVO
     */
    @Override
    public UserVO selectUserByPhone(String phone) {
        UserVO userVO = new UserVO();
        userVO = userMapper.getUserByPhone(phone);
        if (userVO != null) {
            return userVO;
        } else {
            return null;
        }
    }

    /**
     * 发送短信验证码
     *
     * @param phone
     * @return: 短信验证码
     */
    @Override
    public void sendSms(String phone) {
        String code = SmsUtil.setVerificationCode();
        SmsUtil.sendSms(code, phone);
    }

    /**
     * 用户注册：先判断手机号是否已被注册，再判断是否增加用户成功
     *
     * @param registerDTO 注册信息
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultCode userRegister(RegisterDTO registerDTO) {

        Byte salt = Md5EncodeUtil.setSalt();
        String password = registerDTO.getPassword() + salt;
        String phone = registerDTO.getPhone();

        registerDTO.setPassword(Md5EncodeUtil.encode(password));
        registerDTO.setSalt(salt);
        registerDTO.setCreateDate(DateUtil.createTime());
        registerDTO.setName(phone);
        registerDTO.setPhone(phone);

        if (selectUserByPhone(registerDTO.getPhone()) != null) {
            return ResultCode.USER_HAS_EXIST;
        } else {
            if (userMapper.insertUser(registerDTO) > 0) {
                return ResultCode.SUCCESS;
            } else {
                return ResultCode.FAIL;
            }
        }
    }

    /**
     * 用户登录：先判断是否存在手机号，再判断用户密码是否正确
     *
     * @param registerDTO 登录信息
     * @return
     */
    @Override
    public ResultCode userLogin(RegisterDTO registerDTO) {
        if (selectUserByPhone(registerDTO.getPhone()) == null) {
            return ResultCode.USER_NOT_REGISTER;
        } else {
            String salt = userMapper.getSaltByPhone(registerDTO.getPhone());
            String password = registerDTO.getPassword();
            password = Md5EncodeUtil.encode(password + salt);
            registerDTO.setPassword(password);
            UserBO userBO = userMapper.getUserByPhoneAndPassword(registerDTO);
            if (userBO != null) {
                if (userBO.getStatus().equals(Boolean.FALSE)) {
                    return ResultCode.USER_STATUS_DISABLE;
                }
                UserVO userVO = new UserVO();
                userVO.setBO(userBO);
                HttpSession session = request.getSession();
                session.setAttribute(session.getId(), userVO);
                // 初始化购物车
                List<CarItemVO> carItemVOList = new ArrayList<>();
                session.setAttribute(session.getId() + "car", carItemVOList);
                session.setMaxInactiveInterval(60 * 30);

                return ResultCode.SUCCESS;
            } else {
                return ResultCode.USER_LOGIN_ERROR;
            }
        }
    }

    /**
     * 用户修改数据
     *
     * @param userInfoDTO 修改数据
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeUserInfo(UserInfoDTO userInfoDTO) {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        userInfoDTO.setId(userVO.getId());
        userMapper.updateUserInfoByParams(userInfoDTO);
    }

    /**
     * 用户修改头像
     *
     * @param userInfoDTO
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void changeUserHeadImage(UserInfoDTO userInfoDTO) {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        userInfoDTO.setId(userVO.getId());
        userMapper.updateUserHeadImageUrl(userInfoDTO);
    }

    /**
     * 前台-查询用户信息
     *
     * @return
     */
    @Override
    public UserInfoVO queryUserInfo() {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        UserInfoBO userInfoBO = userMapper.getUserInfo(userVO.getId());
        if (userInfoBO != null) {
            UserInfoVO userInfoVO = new UserInfoVO();
            userInfoVO.setBO(userInfoBO);
            return userInfoVO;
        } else {
            return null;
        }
    }

    /**
     * 获取收货人信息
     *
     * @return 收货人信息
     */
    @Override
    public ReceiverVO getReceiver() {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        ReceiverVO receiverVO = userMapper.getReceiver(userVO.getId());
        return receiverVO;
    }

    /**
     * 后台查询所有用户
     *
     * @return
     */
    @Override
    public List<UserVO> getUserList() {
        List<UserBO> userBOS = userMapper.getUserList();
        List<UserVO> userVOS = new ArrayList<>();
        for (UserBO userBO : userBOS) {
            UserVO userVO = new UserVO(userBO);
            userVOS.add(userVO);
        }
        return userVOS;
    }

    /**
     * 修改用户状态
     *
     * @param userDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateUserStatus(UserDTO userDTO) {
        userMapper.updateUserStatusById(userDTO);
    }

    /**
     * 动态查询用户
     *
     * @param userDTO 查询信息
     * @return
     */
    @Override
    public List<UserVO> getUserByParams(UserDTO userDTO) {
        List<UserBO> userBOS = userMapper.getUserListByParams(userDTO);
        List<UserVO> userVOS = new ArrayList<>();
        if (userBOS.size() < 1) {
            return null;
        } else {
            for (UserBO userBO : userBOS) {
                UserVO userVO = new UserVO(userBO);
                userVOS.add(userVO);
            }
        }
        return userVOS;
    }

    /**
     * 更新用户总积分
     *
     * @param score 积分增量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addTotalScore(Integer score) {
        UserVO userVO = (UserVO) session.getAttribute(session.getId());
        userMapper.updateTotalScore(userVO.getId(), score);
    }
}
