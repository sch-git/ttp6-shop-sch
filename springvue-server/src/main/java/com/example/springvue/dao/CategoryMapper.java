package com.example.springvue.dao;

import com.example.springvue.pojo.bo.CategoryBO;
import com.example.springvue.pojo.bo.UserCategoryBO;
import com.example.springvue.pojo.dto.CategoryDTO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:32
 */
@Repository
public interface CategoryMapper {

    /**
     * 增加分类
     *
     * @param categoryDTO
     * @return
     */
    Integer insertCategory(CategoryDTO categoryDTO);

    /**
     * 查询状态正常的父分类
     *
     * @return
     */
    List<CategoryBO> getParentCategoryNameList();

    /**
     * 查询分类
     *
     * @return
     */
    List<CategoryBO> getCategoryList();

    /**
     * 修改分类状态
     *
     * @param categoryDTO 分类数据
     * @return
     */
    Integer updateCategoryStatus(CategoryDTO categoryDTO);

    /**
     * 根据分类名和分类状态模糊查询
     *
     * @param categoryDTO 分类数据
     * @return
     */
    List<CategoryBO> getCategoryListByParams(CategoryDTO categoryDTO);

    /**
     * 修改分类
     *
     * @param categoryDTO 修改的分类数据
     * @return
     */
    Integer updateCategory(CategoryDTO categoryDTO);

    /**
     * 前台-首页查询父分类
     *
     * @return 父分类集合
     */
    List<UserCategoryBO> getParentCategoryList();

    /**
     * 根据分类id查询父分类
     *
     * @param id 分类id
     * @return
     */
    CategoryBO getParentCategoryById(Long id);
}