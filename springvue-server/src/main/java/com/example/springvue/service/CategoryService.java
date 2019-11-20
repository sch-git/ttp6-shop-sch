package com.example.springvue.service;

import com.example.springvue.pojo.dto.CategoryDTO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.vo.CategoryVO;
import com.example.springvue.pojo.vo.SelectedGoodCategoryVO;

import java.util.List;

/**
 * @Description: 分类操作
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/19 12:45
 */
public interface CategoryService {
    /**
     * 增加分类
     *
     * @param categoryDTO
     * @return
     */
    void addCategory(CategoryDTO categoryDTO);

    /**
     * 获取状态正常的父分类
     *
     * @return
     */
    List<CategoryVO> getParentCategory();

    /**
     * 获取所有分类
     *
     * @return
     */
    List<CategoryVO> getCategoryList();

    /**
     * 修改分类状态
     *
     * @param categoryDTO
     * @return
     */
    void updateCategoryStatus(CategoryDTO categoryDTO);

    /**
     * 修改分类：名称，父分类，状态
     *
     * @param categoryDTO
     * @return
     */
    void updateCategory(CategoryDTO categoryDTO);

    /**
     * 根据分类名和分类状态模糊查询
     *
     * @param categoryDTO
     * @return
     */
    List<CategoryVO> getCategoryListByParams(CategoryDTO categoryDTO);


    /**
     * 根据商品id获取所属分类列表
     *
     * @param goodId 商品id
     * @return 所属分类列表
     */
    List<SelectedGoodCategoryVO> getCategoryIdsByGoodId(Long goodId);

    /**
     * 根据商品id删除所属分类
     *
     * @param goodId 商品id
     * @return 删除结果
     */
    void deleteCategoryListByGoodId(Long goodId);

    /**
     * 添加商品的分类
     *
     * @param goodCategoryDTOS 商品分类数据集合
     */
    void addGoodCategoryList(List<GoodCategoryDTO> goodCategoryDTOS);

    /**
     * 查询父分类
     *
     * @param id 分类id
     * @return
     */
    CategoryVO getParentCategoryById(Long id);
}
