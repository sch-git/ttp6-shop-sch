package com.example.springvue.service.impl;

import com.example.springvue.dao.AdminMapper;
import com.example.springvue.pojo.dto.AdminDTO;
import com.example.springvue.pojo.vo.AdminVO;
import com.example.springvue.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/18 10:45
 */
@Service
public class AdminServiceImpl implements AdminService {
    @Autowired
    private AdminMapper adminMapper;
    @Autowired
    private HttpSession session;
    private static String adminStr = "admin";

    /**
     * 根据用户名密码查询管理员
     *
     * @param adminDTO
     * @return
     */
    @Override
    public boolean getAdmin(AdminDTO adminDTO) {
        AdminVO adminVO = adminMapper.getAdminByNameAndPassword(adminDTO);
        if (adminVO != null) {
            // 管理员登陆成功
            session.setAttribute(session.getId() + adminStr, adminVO);
            session.setMaxInactiveInterval(60 * 30);
            return true;
        } else {
            return false;
        }
    }

    /**
     * 查询管理员信息
     *
     * @return
     */
    @Override
    public AdminVO getAdmin() {
        AdminVO admin = (AdminVO) session.getAttribute(session.getId() + adminStr);
        return admin;
    }
}
