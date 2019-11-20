package com.example.springvue.dao;

import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.vo.GoodInfoCategoryVO;
import com.example.springvue.pojo.vo.SelectedGoodCategoryVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:54
 */
@Repository
public interface GoodCategoryMapper {

    /**
     * 根据商品id获取所属分类列表
     *
     * @param goodId 商品id
     * @return 所属分类列表
     */
    List<SelectedGoodCategoryVO> getCategoryListByGoodId(Long goodId);

    /**
     * 根据商品id删除所属分类
     *
     * @param goodId 商品id
     * @return
     */
    Integer deleteCategoryListByGoodId(Long goodId);

    /**
     * 插入商品分类数据
     *
     * @param goodCategoryDTOS 商品分类数据集合
     * @return
     */
    Integer insertGoodCategoryList(List<GoodCategoryDTO> goodCategoryDTOS);

    /**
     * 前台查询商品所有分类
     *
     * @param goodId 商品id
     * @return 商品分类
     */
    List<GoodInfoCategoryVO> getGoodInfoCategoryById(Long goodId);
}