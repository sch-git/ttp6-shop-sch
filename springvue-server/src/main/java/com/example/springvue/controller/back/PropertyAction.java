package com.example.springvue.controller.back;

import com.example.springvue.pojo.ao.PropertyAO;
import com.example.springvue.pojo.ao.PropertyValueAO;
import com.example.springvue.pojo.bo.PropertyBO;
import com.example.springvue.pojo.dto.PropertyDTO;
import com.example.springvue.pojo.dto.PropertyValueDTO;
import com.example.springvue.pojo.vo.PropertyVO;
import com.example.springvue.pojo.vo.PropertyValueVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.service.PropertyService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @Description: 属性管理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 21:06
 */
@Api(tags = "后台属性")
@RestController
@RequestMapping("/lucky/admin/property")
public class PropertyAction {
    @Autowired
    private PropertyService propertyService;

    /**
     * 增加商品属性值
     *
     * @param propertyValueAO 商品属性值信息
     * @return
     */
    @PostMapping("/value")
    public Result addGoodPropertyValue(@RequestBody PropertyValueAO propertyValueAO) {
        PropertyValueDTO propertyValueDTO = new PropertyValueDTO();
        propertyValueDTO.setAO(propertyValueAO);
        propertyService.addGoodPropertyValue(propertyValueDTO);
        return Result.success();
    }

    /**
     * 根据商品id查询没有的属性
     *
     * @param goodId 商品id
     * @return 商品没有的属性列表
     */
    @GetMapping("/list")
    public Result getGoodNoProperty(@RequestParam(value = "goodId") Long goodId) {
        List<PropertyBO> propertyBOList = propertyService.queryPropertyListByGoodId(goodId);
        return Result.success(propertyBOList);
    }

    /**
     * 新增属性
     *
     * @param propertyAO 属性信息
     * @return
     */
    @PostMapping("/property")
    public Result addProperty(@RequestBody PropertyAO propertyAO) {
        PropertyDTO propertyDTO = new PropertyDTO(propertyAO);
        propertyService.addProperty(propertyDTO);
        return Result.success();
    }

    /**
     * 获取所有属性
     *
     * @return
     */
    @GetMapping("/propertyList")
    public Result getProperties() {
        List<PropertyVO> propertyVOList = propertyService.getPropertyList();
        if (propertyVOList != null) {
            return Result.success(propertyVOList);
        } else {
            return Result.fail();
        }
    }

    /**
     * 模糊查询属性
     *
     * @param propertyAO 查询条件
     * @return 属性列表
     */
    @PostMapping("/propertyList")
    public Result queryProperty(@RequestBody PropertyAO propertyAO) {
        PropertyDTO propertyDTO = new PropertyDTO(propertyAO);
        List<PropertyVO> propertyVOList = propertyService.queryProperty(propertyDTO);
        if (propertyVOList != null) {
            return Result.success();
        } else {
            return Result.fail();
        }
    }

    /**
     * 修改属性名
     *
     * @param propertyAO 属性信息
     * @return
     */
    @PutMapping("/property")
    public Result changePropertyName(@RequestBody PropertyAO propertyAO) {

        PropertyDTO propertyDTO = new PropertyDTO(propertyAO);
        PropertyVO propertyVO = propertyService.changePropertyName(propertyDTO);
        if (propertyVO != null) {
            return Result.success(propertyVO);
        } else {
            return Result.fail();
        }
    }

    /**
     * 根据属性id查询所有属值
     *
     * @param propertyId 属性id
     * @return
     */
    @GetMapping("/valueList")
    public Result getPropertyListById(@RequestParam(value = "propertyId") Long propertyId) {
        List<PropertyValueVO> propertyValueVOS = propertyService.queryPropertyValueList(propertyId);
        return Result.success(propertyValueVOS);
    }

    /**
     * 修改属性值
     *
     * @param propertyValueAO 属性值信息
     * @return
     */
    @PutMapping("/value")
    public Result changePropertyValue(@RequestBody PropertyValueAO propertyValueAO) {
        PropertyValueDTO propertyValueDTO = new PropertyValueDTO();
        propertyValueDTO.setAO(propertyValueAO);
        propertyService.changePropertyValueById(propertyValueDTO);
        return Result.success();
    }
}
