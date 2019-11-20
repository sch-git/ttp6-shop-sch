package com.example.springvue.service.impl;

import com.example.springvue.dao.CategoryMapper;
import com.example.springvue.dao.GoodCategoryMapper;
import com.example.springvue.pojo.bo.CategoryBO;
import com.example.springvue.pojo.dto.CategoryDTO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.vo.AdminVO;
import com.example.springvue.pojo.vo.CategoryVO;
import com.example.springvue.pojo.vo.SelectedGoodCategoryVO;
import com.example.springvue.service.CategoryService;
import com.example.springvue.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/19 12:45
 */
@Service
public class CategoryServiceImpl implements CategoryService {
    @Autowired
    private CategoryMapper categoryMapper;
    @Autowired
    private GoodCategoryMapper goodCategoryMapper;
    @Autowired
    private HttpSession session;
    private static String adminStr = "admin";

    /**
     * 增加分类
     *
     * @param categoryDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addCategory(CategoryDTO categoryDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);

        categoryDTO.setCreateBy(adminVO.getId());
        categoryDTO.setCreateTime(DateUtil.createTime());
        categoryDTO.setLastModifyBy(adminVO.getId());
        categoryDTO.setLastModifyTime(DateUtil.createTime());
        categoryMapper.insertCategory(categoryDTO);
    }

    /**
     * 获取状态正常的父分类
     *
     * @return
     */
    @Override
    public List<CategoryVO> getParentCategory() {
        List<CategoryBO> categoryBOS = categoryMapper.getParentCategoryNameList();
        List<CategoryVO> categoryVOS = new ArrayList<>();
        if (categoryBOS.size() > 0) {
            for (CategoryBO categoryBO : categoryBOS) {
                CategoryVO categoryVO = new CategoryVO(categoryBO);
                categoryVOS.add(categoryVO);
            }
            return categoryVOS;
        } else {
            return null;
        }
    }

    /**
     * 获取所有分类
     *
     * @return
     */
    @Override
    public List<CategoryVO> getCategoryList() {
        List<CategoryBO> categoryBOS = categoryMapper.getCategoryList();
        List<CategoryVO> categoryVOS = new ArrayList<>();
        if (categoryBOS.size() > 0) {
            for (CategoryBO categoryBO : categoryBOS) {
                CategoryVO categoryVO = new CategoryVO(categoryBO);
                categoryVOS.add(categoryVO);
            }
            return categoryVOS;
        } else {
            return null;
        }
    }

    /**
     * 修改分类状态
     *
     * @param categoryDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategoryStatus(CategoryDTO categoryDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);

        categoryDTO.setLastModifyBy(adminVO.getId());
        categoryDTO.setLastModifyTime(DateUtil.createTime());
        categoryMapper.updateCategoryStatus(categoryDTO);
    }

    /**
     * 修改分类：名称，父分类，状态
     *
     * @param categoryDTO
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void updateCategory(CategoryDTO categoryDTO) {
        AdminVO adminVO = (AdminVO) session.getAttribute(session.getId() + adminStr);

        categoryDTO.setLastModifyBy(adminVO.getId());
        categoryDTO.setLastModifyTime(DateUtil.createTime());
        categoryMapper.updateCategory(categoryDTO);
    }

    /**
     * 根据分类名和分类状态模糊查询
     *
     * @param categoryDTO
     * @return
     */
    @Override
    public List<CategoryVO> getCategoryListByParams(CategoryDTO categoryDTO) {
        List<CategoryBO> categoryBOS = categoryMapper.getCategoryListByParams(categoryDTO);
        if (categoryBOS.size() < 1) {
            return null;
        } else {
            List<CategoryVO> categoryVOS = new ArrayList<>();
            for (CategoryBO categoryBO : categoryBOS) {
                CategoryVO categoryVO = new CategoryVO(categoryBO);
                categoryVOS.add(categoryVO);
            }
            return categoryVOS;
        }
    }

    /**
     * 根据商品id获取所属分类列表
     *
     * @param goodId 商品id
     * @return 所属分类列表
     */
    @Override
    public List<SelectedGoodCategoryVO> getCategoryIdsByGoodId(Long goodId) {
        List<SelectedGoodCategoryVO> goodCategoryVO = goodCategoryMapper.getCategoryListByGoodId(goodId);
        return goodCategoryVO;
    }

    /**
     * 根据商品id删除所属分类
     *
     * @param goodId 商品id
     * @return 删除结果
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void deleteCategoryListByGoodId(Long goodId) {
        goodCategoryMapper.deleteCategoryListByGoodId(goodId);
    }

    /**
     * 添加商品分类
     *
     * @param goodCategoryDTOS 商品分类数据集合
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addGoodCategoryList(List<GoodCategoryDTO> goodCategoryDTOS) {
        goodCategoryMapper.insertGoodCategoryList(goodCategoryDTOS);
    }

    /**
     * 查询父分类
     *
     * @param id 分类id
     * @return
     */
    @Override
    public CategoryVO getParentCategoryById(Long id) {
        CategoryVO categoryVO = new CategoryVO();
        CategoryBO categoryBO = categoryMapper.getParentCategoryById(id);
        categoryVO.setBO(categoryBO);
        return categoryVO;
    }
}
