package com.example.springvue.controller.front;

import com.example.springvue.service.CategoryService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Description: 前台-分类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 22:21
 */
@Api(tags = "前台-分类")
@RestController
@RequestMapping("/lucky/category")
public class CategoryController {
    @Autowired
    private CategoryService categoryService;
}
