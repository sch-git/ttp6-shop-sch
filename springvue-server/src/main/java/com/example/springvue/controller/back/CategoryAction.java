package com.example.springvue.controller.back;

import com.example.springvue.pojo.ao.CategoryAO;
import com.example.springvue.pojo.ao.GoodCategoryAO;
import com.example.springvue.pojo.dto.CategoryDTO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.vo.*;
import com.example.springvue.service.CategoryService;
import com.example.springvue.util.DateUtil;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 后台-分类
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 17:26
 */
@Api(tags = "后台分类")
@RestController
@RequestMapping("/lucky/admin/category")
public class CategoryAction {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private HttpSession session;
    private static String adminStr = "admin";

    /**
     * 获取分类的父分类
     *
     * @param id 分类id
     * @return
     */
    @GetMapping("/parent")
    public Result getParentCategory(@RequestParam(value = "id") Long id) {
        CategoryVO categoryVO = categoryService.getParentCategoryById(id);
        return Result.success(categoryVO);
    }

    /**
     * 获取状态正常的父分类
     *
     * @return
     */
    @GetMapping("/names")
    public Result getParentCategoryNames() {
        List<CategoryVO> categoryVOList = categoryService.getParentCategory();
        if (categoryVOList != null) {
            return Result.success(categoryVOList);
        } else {
            return Result.success(ResultCode.FAIL);
        }
    }

    /**
     * 增加分类
     *
     * @param categoryAO 分类信息
     * @return
     */
    @PostMapping("/category")
    public Result addCategory(@RequestBody CategoryAO categoryAO) {
        CategoryDTO categoryDTO = new CategoryDTO(categoryAO);
        categoryService.addCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 获取所有分类
     *
     * @return
     */
    @GetMapping("/categoryAll")
    public Result getCategory() {
        List<CategoryVO> categoryVOList = categoryService.getCategoryList();
        return Result.success(categoryVOList);
    }

    /**
     * 修改分类状态
     *
     * @param categoryAO 分类信息
     * @return
     */
    @PostMapping("/status")
    public Result updateCategoryStatus(@RequestBody CategoryAO categoryAO) {
        CategoryDTO categoryDTO = new CategoryDTO(categoryAO);
        categoryService.updateCategoryStatus(categoryDTO);
        return Result.success();
    }

    /**
     * 根据分类名和分类状态模糊查询
     *
     * @param categoryAO 分类信息
     * @return
     */
    @PostMapping("/list")
    public Result getCategoryListByParams(@RequestBody CategoryAO categoryAO) {
        CategoryDTO categoryDTO = new CategoryDTO(categoryAO);
        List<CategoryVO> categoryVOList = categoryService.getCategoryListByParams(categoryDTO);
        if (categoryVOList != null) {
            return Result.success(categoryVOList);
        } else {
            return Result.fail(ResultCode.FAIL);
        }
    }

    /**
     * 修改分类
     *
     * @param categoryAO 分类信息
     * @return
     */
    @PutMapping("/category")
    public Result updateCategory(@RequestBody CategoryAO categoryAO) {
        CategoryDTO categoryDTO = new CategoryDTO(categoryAO);
        categoryService.updateCategory(categoryDTO);
        return Result.success();
    }

    /**
     * 根据商品id获取所属分类列表
     *
     * @param id 商品id
     * @return 所属分类列表
     */
    @GetMapping("/list")
    public Result getCategoryByGoodId(@RequestParam(value = "id") Long id) {
        List<SelectedGoodCategoryVO> goodCategoryVOS = categoryService.getCategoryIdsByGoodId(id);
        return Result.success(goodCategoryVOS);
    }

    /**
     * 修改商品分类
     *
     * @param goodCategoryAOS 商品分类
     * @return
     */
    @PostMapping("/goodCategory")
    public Result updateGoodCategory(@RequestBody List<GoodCategoryAO> goodCategoryAOS) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);
        //根据商品id，删除所属分类
        categoryService.deleteCategoryListByGoodId(goodCategoryAOS.get(0).getGoodId());
        // 插入商品分类数据
        List<GoodCategoryDTO> goodCategoryDTOS = new ArrayList<>();
        for (GoodCategoryAO goodCategoryAO : goodCategoryAOS) {
            GoodCategoryDTO goodCategoryDTO = new GoodCategoryDTO();
            goodCategoryDTO.setCategoryId(adminVO.getId());
            goodCategoryDTO.setLastModifyBy(adminVO.getId());
            goodCategoryDTO.setAO(goodCategoryAO);
            goodCategoryDTO.setCreateTime(DateUtil.createTime());
            goodCategoryDTO.setLastModifyTime(DateUtil.createTime());
            goodCategoryDTOS.add(goodCategoryDTO);
        }
        categoryService.addGoodCategoryList(goodCategoryDTOS);
        return Result.success();
    }

}
