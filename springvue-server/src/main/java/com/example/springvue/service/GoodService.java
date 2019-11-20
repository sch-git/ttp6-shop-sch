package com.example.springvue.service;

import com.example.springvue.pojo.bo.UserCategoryBO;
import com.example.springvue.pojo.bo.UserGoodBO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.dto.GoodDTO;
import com.example.springvue.pojo.dto.UserSearchGoodDTO;
import com.example.springvue.pojo.vo.GoodInfoVO;
import com.example.springvue.pojo.vo.GoodVO;

import java.util.List;

/**
 * @Description: 商品操作
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/21 09:58
 */
public interface GoodService {
    /**
     * 增加商品
     *
     * @param goodDTO 增加的商品数据
     * @return
     */
    void addGood(GoodDTO goodDTO);

    /**
     * 获取所有商品
     *
     * @return
     */
    List<GoodVO> getGoodList();

    /**
     * 修改商品状态
     *
     * @param goodDTO 修改的商品数据
     * @return
     */
    void updateGoodStatus(GoodDTO goodDTO);

    /**
     * 修改商品：名称，价格，库存，描述，状态
     *
     * @param goodDTO
     * @return
     */
    void updateGood(GoodDTO goodDTO);

    /**
     * 根据数据模糊查询商品
     *
     * @param goodCategoryDTO 查询数据
     * @return 商品列表
     */
    List<GoodVO> getGoodListByParams(GoodCategoryDTO goodCategoryDTO);

    /**
     * 根据分类id查询该分类及其子类的商品
     *
     * @return
     */
    List<UserCategoryBO> getUserGoodListByCategoryId();

    /**
     * 用户查询商品
     *
     * @param userSearchGoodDTO 查询数据
     * @return 商品数据
     */
    List<UserGoodBO> getUserGoodList(UserSearchGoodDTO userSearchGoodDTO);

    /**
     * 前台-商品基本信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    GoodInfoVO getGoodInfo(Long id);

    /**
     * 前台生成订单-更新商品库存
     *
     * @param id    商品id
     * @param stock 商品库存变化量
     */
    void updateGoodStock(Long id, Integer stock);

    /**
     * 取消订单-恢复库存
     *
     * @param id    商品id
     * @param stock 商品库存变化量
     */
    void restoreGoodStock(Long id, Integer stock);
}
