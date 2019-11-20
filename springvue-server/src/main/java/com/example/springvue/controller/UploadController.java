package com.example.springvue.controller;

import com.example.springvue.pojo.vo.Result;
import com.example.springvue.pojo.vo.ResultCode;
import com.example.springvue.util.UploadUtil;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @Description: 上传接口
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 22:43
 */
@Api(tags = "上传接口")
@RestController
@RequestMapping("/lucky/upload")
public class UploadController {
    /**
     * 图片上传
     *
     * @param file 图片
     * @return
     */
    @PostMapping("/image")
    public Result uploadGoodImage(@RequestParam("picture") MultipartFile file) {
        String path = UploadUtil.uploadImage(file);
        if (path != null) {
            return Result.success(path);
        } else {
            return Result.fail(ResultCode.UPLOAD_ERROR);
        }
    }
}
