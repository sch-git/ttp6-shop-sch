package com.example.springvue.controller.back;

import com.example.springvue.pojo.ao.AdminAO;
import com.example.springvue.pojo.dto.AdminDTO;
import com.example.springvue.pojo.vo.AdminVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.pojo.vo.ResultCode;
import com.example.springvue.service.AdminService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @Description: 后台-管理员
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 15:33
 */

@Api(tags = "管理员")
@RestController
@RequestMapping("/lucky")
public class AdminAction {

    @Autowired
    private AdminService adminService;

    /**
     * 后台管理
     *
     * @return
     */
    @GetMapping("/admin")
    public Result getAdminInfo() {
        AdminVO admin = adminService.getAdmin();
        return Result.success(admin.getName());
    }

    /**
     * 管理员登陆
     *
     * @param adminAO 管理员信息
     * @return
     */
    @PostMapping("/admin/login")
    public Result adminLogin(@RequestBody AdminAO adminAO) {
        AdminDTO adminDTO = new AdminDTO();
        adminDTO.setAO(adminAO);
        if (adminService.getAdmin(adminDTO)) {
            return Result.success();
        } else {
            return Result.fail(ResultCode.ADMIN_LOGIN_ERROR);
        }
    }
}
