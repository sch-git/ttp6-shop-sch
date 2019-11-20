package com.example.springvue.dao;

import com.example.springvue.pojo.dto.AdminDTO;
import com.example.springvue.pojo.vo.AdminVO;
import org.springframework.stereotype.Repository;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:25
 */
@Repository
public interface AdminMapper {
    /**
     * 根据用户名密码查询管理员
     *
     * @param adminDTO 管理员用户名，密码
     * @return
     */
    AdminVO getAdminByNameAndPassword(AdminDTO adminDTO);
}