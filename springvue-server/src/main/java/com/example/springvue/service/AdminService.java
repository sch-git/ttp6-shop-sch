package com.example.springvue.service;

import com.example.springvue.pojo.dto.AdminDTO;
import com.example.springvue.pojo.vo.AdminVO;

/**
 * @Description: 管理员
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/18 10:45
 */
public interface AdminService {

    /**
     * 根据用户名密码查询管理员
     *
     * @param adminDTO
     * @return
     */
    boolean getAdmin(AdminDTO adminDTO);

    /**
     * 查询管理员信息
     *
     * @return
     */
    AdminVO getAdmin();
}
