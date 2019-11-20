package com.example.springvue.service.impl;

import com.example.springvue.dao.CategoryMapper;
import com.example.springvue.dao.GoodCategoryMapper;
import com.example.springvue.dao.GoodImageMapper;
import com.example.springvue.dao.GoodMapper;
import com.example.springvue.pojo.bo.GoodBO;
import com.example.springvue.pojo.bo.GoodInfoBO;
import com.example.springvue.pojo.bo.UserCategoryBO;
import com.example.springvue.pojo.bo.UserGoodBO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.dto.GoodDTO;
import com.example.springvue.pojo.dto.UserSearchGoodDTO;
import com.example.springvue.pojo.vo.AdminVO;
import com.example.springvue.pojo.vo.GoodInfoCategoryVO;
import com.example.springvue.pojo.vo.GoodInfoVO;
import com.example.springvue.pojo.vo.GoodVO;
import com.example.springvue.service.GoodService;
import com.example.springvue.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品操作
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/21 09:59
 */
@Service
public class GoodServiceImpl implements GoodService {
    @Autowired
    private GoodMapper goodMapper;
    @Autowired
    private GoodCategoryMapper goodCategoryMapper;
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private HttpSession session;
    @Autowired
    private GoodImageMapper goodImageMapper;
    private static String adminStr = "admin";

    /**
     * 增加商品
     *
     * @param goodDTO 增加的商品数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGood(GoodDTO goodDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);

        goodDTO.setCreateBy(adminVO.getId());
        goodDTO.setCreateTime(DateUtil.createTime());
        goodDTO.setLastModifyBy(adminVO.getId());
        goodDTO.setLastModifyTime(DateUtil.createTime());
        goodMapper.insertGood(goodDTO);
        return;
    }

    /**
     * 获取所有商品
     *
     * @return
     */
    @Override
    public List<GoodVO> getGoodList() {
        List<GoodBO> goodBOS = goodMapper.getGoodList();
        if (goodBOS.size() > 0) {
            List<GoodVO> goodVOS = new ArrayList<>();
            for (GoodBO goodBO : goodBOS) {
                GoodVO goodVO = new GoodVO(goodBO);
                goodVOS.add(goodVO);
            }
            return goodVOS;
        } else {
            return null;
        }
    }

    /**
     * 修改商品状态
     *
     * @param goodDTO 待更新的商品数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGoodStatus(GoodDTO goodDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);

        goodDTO.setLastModifyTime(DateUtil.createTime());
        goodDTO.setLastModifyBy(adminVO.getId());
        goodMapper.updateGoodStatus(goodDTO);

    }

    /**
     * 修改商品：名称，价格，库存，描述，状态
     *
     * @param goodDTO 修改的商品数据
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGood(GoodDTO goodDTO) {

        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);
        goodDTO.setLastModifyBy(adminVO.getId());
        goodDTO.setLastModifyTime(DateUtil.createTime());

        goodMapper.updateGood(goodDTO);
    }

    /**
     * 根据数据模糊查询商品
     *
     * @param goodCategoryDTO 查询数据
     * @return 商品列表
     */
    @Override
    public List<GoodVO> getGoodListByParams(GoodCategoryDTO goodCategoryDTO) {
        List<GoodBO> goodBOS = goodMapper.getGoodListByParams(goodCategoryDTO);
        List<GoodVO> goodVOS = new ArrayList<>();
        if (goodBOS.size() > 0) {
            for (GoodBO goodBO : goodBOS) {
                GoodVO goodVO = new GoodVO(goodBO);
                goodVOS.add(goodVO);
            }
        }
        return goodVOS;
    }

    /**
     * 根据分类id查询该分类及其子类的商品
     *
     * @return
     */
    @Override
    public List<UserCategoryBO> getUserGoodListByCategoryId() {
        List<UserCategoryBO> userCategoryBOS = categoryMapper.getParentCategoryList();
        for (UserCategoryBO userCategoryBO : userCategoryBOS) {
            List<UserGoodBO> userGoodBOS = goodMapper.getUserGoodByCategoryId(userCategoryBO.getId());
            userCategoryBO.setGoodList(userGoodBOS);
        }
        return userCategoryBOS;
    }

    /**
     * 用户查询商品
     *
     * @param userSearchGoodDTO 查询数据
     * @return 商品数据
     */
    @Override
    public List<UserGoodBO> getUserGoodList(UserSearchGoodDTO userSearchGoodDTO) {
        List<UserGoodBO> userGoodBOList = goodMapper.getUserGoodByParams(userSearchGoodDTO);
        return userGoodBOList;
    }

    /**
     * 前台-商品基本信息
     *
     * @param id 商品id
     * @return 商品信息
     */
    @Override
    public GoodInfoVO getGoodInfo(Long id) {
        GoodInfoVO goodInfoVO = new GoodInfoVO();
        GoodInfoBO goodInfoBO = goodMapper.getGoodInfoById(id);
        List<String> urls = goodImageMapper.getGoodInfoUrlListByGoodId(id);
        List<GoodInfoCategoryVO> categoryList = goodCategoryMapper.getGoodInfoCategoryById(id);

        goodInfoVO.setBO(goodInfoBO);
        goodInfoVO.setUrlList(urls);
        goodInfoVO.setCategoryList(categoryList);
        return goodInfoVO;
    }

    /**
     * 前台生成订单-更新商品库存
     *
     * @param id    商品id
     * @param stock 商品库存变化量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateGoodStock(Long id, Integer stock) {
        goodMapper.updateGoodStock(id, stock);
    }

    /**
     * 取消订单-恢复库存
     *
     * @param id    商品id
     * @param stock 商品库存变化量
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void restoreGoodStock(Long id, Integer stock) {
        goodMapper.restoreGoodStock(id, stock);
    }
}
