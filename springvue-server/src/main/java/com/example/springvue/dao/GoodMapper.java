package com.example.springvue.dao;

import com.example.springvue.pojo.bo.GoodBO;
import com.example.springvue.pojo.bo.GoodInfoBO;
import com.example.springvue.pojo.bo.UserGoodBO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.dto.GoodDTO;
import com.example.springvue.pojo.dto.UserSearchGoodDTO;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 14:34
 */
@Repository
public interface GoodMapper {
    /**
     * 添加商品
     *
     * @param goodDTO
     * @return
     */
    Integer insertGood(GoodDTO goodDTO);

    /**
     * 查询所有商品
     *
     * @return
     */
    List<GoodBO> getGoodList();

    /**
     * 修改商品状态
     *
     * @param goodDTO
     * @return
     */
    Integer updateGoodStatus(GoodDTO goodDTO);

    /**
     * 动态修改商品数据
     *
     * @param goodDTO 修改的数据
     * @return
     */
    Integer updateGood(GoodDTO goodDTO);

    /**
     * 根据数据模糊查询商品
     *
     * @param goodCategoryDTO 查询数据
     * @return 商品列表
     */
    List<GoodBO> getGoodListByParams(GoodCategoryDTO goodCategoryDTO);

    /**
     * 根据分类id查询该分类及其子类的商品
     *
     * @param id 父分类id
     * @return
     */
    List<UserGoodBO> getUserGoodByCategoryId(Long id);

    /**
     * 用户查询商品
     *
     * @param userSearchGoodDTO 查询数据，categoryId,goodName
     * @return 商品信息
     */
    List<UserGoodBO> getUserGoodByParams(UserSearchGoodDTO userSearchGoodDTO);

    /**
     * 前台-商品基本信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    GoodInfoBO getGoodInfoById(Long id);

    /**
     * 生成订单，更新商品库存
     *
     * @param id    商品id
     * @param stock 商品库存变化量
     */
    void updateGoodStock(@Param(value = "id") Long id, @Param(value = "stock") Integer stock);

    /**
     * 取消订单后恢复商品库存
     *
     * @param id    商品id
     * @param stock 商品库存变化量
     */
    void restoreGoodStock(@Param(value = "id") Long id, @Param(value = "stock") Integer stock);
}