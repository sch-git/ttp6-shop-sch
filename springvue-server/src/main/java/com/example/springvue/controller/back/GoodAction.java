package com.example.springvue.controller.back;

import com.example.springvue.pojo.ao.GoodAO;
import com.example.springvue.pojo.ao.GoodCategoryAO;
import com.example.springvue.pojo.ao.GoodImageAO;
import com.example.springvue.pojo.dto.GoodCategoryDTO;
import com.example.springvue.pojo.dto.GoodDTO;
import com.example.springvue.pojo.dto.GoodImageDTO;
import com.example.springvue.pojo.vo.GoodVO;
import com.example.springvue.pojo.vo.Result;
import com.example.springvue.pojo.vo.ResultCode;
import com.example.springvue.service.GoodImageService;
import com.example.springvue.service.GoodService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description: 商品管理
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/30 20:52
 */
@Api(tags = "后台商品")
@RestController
@RequestMapping("/lucky/admin/good")
public class GoodAction {

    @Autowired
    private GoodService goodService;
    @Autowired
    private GoodImageService goodImageService;

    /**
     * 根据数据模糊查询商品
     *
     * @param goodCategoryAO 查询数据
     * @return 商品列表
     */
    @PostMapping("/list")
    public Result getGoodListByParams(@RequestBody GoodCategoryAO goodCategoryAO) {
        GoodCategoryDTO goodCategoryDTO = new GoodCategoryDTO();
        goodCategoryDTO.setAO(goodCategoryAO);
        List<GoodVO> goodVOList = goodService.getGoodListByParams(goodCategoryDTO);
        if (goodVOList != null) {
            return Result.success(goodVOList);
        } else {
            return Result.fail(ResultCode.FAIL);
        }
    }

    /**
     * 修改商品状态
     *
     * @param goodAO 商品信息
     * @return
     */
    @PutMapping("/status")
    public Result updateGoodStatus(@RequestBody GoodAO goodAO) {
        GoodDTO goodDTO = new GoodDTO(goodAO);
        goodService.updateGoodStatus(goodDTO);
        return Result.success();
    }

    /**
     * 增加商品
     *
     * @param goodAO 增加的商品数据
     * @return
     */
    @PostMapping("/good")
    public Result addGood(@RequestBody GoodAO goodAO) {
        GoodDTO goodDTO = new GoodDTO(goodAO);
        goodService.addGood(goodDTO);
        return Result.success();
    }

    /**
     * 修改商品
     *
     * @param goodAO 修改的商品数据
     * @return
     */
    @PutMapping("/good")
    public Result updateGood(@RequestBody GoodAO goodAO) {
        GoodDTO goodDTO = new GoodDTO(goodAO);
        goodService.updateGood(goodDTO);
        return Result.success();
    }

    /**
     * 获取所有商品
     *
     * @return
     */
    @GetMapping("/list")
    public Result getGood() {
        List<GoodVO> goodVOList = goodService.getGoodList();
        if (goodVOList != null) {
            return Result.success(goodVOList);
        } else {
            return Result.fail(ResultCode.FAIL);
        }
    }

    /**
     * 根据商品id获取商品图片
     *
     * @param goodId 商品id
     * @return 图片urlList
     */
    @GetMapping("/images")
    public Result getImagesByGoodId(@RequestParam(value = "goodId") Long goodId) {
        List<String> urlList = goodImageService.getImageUrlByGoodId(goodId);
        return Result.success(urlList);
    }

    /**
     * 保存商品图片url
     *
     * @param goodImageAOS 商品图片数据
     * @return
     */
    @PostMapping("/images")
    public Result addGoodImage(@RequestBody List<GoodImageAO> goodImageAOS) {
        if (goodImageAOS.size() > 0) {
            List<GoodImageDTO> goodImageDTOS = new ArrayList<>();
            for (GoodImageAO goodImageAO : goodImageAOS) {
                GoodImageDTO goodImageDTO = new GoodImageDTO();
                goodImageDTO.setAO(goodImageAO);
                goodImageDTOS.add(goodImageDTO);
            }
            goodImageService.addGoodImageList(goodImageDTOS);
            return Result.success();
        } else {
            return Result.fail();
        }
    }

}
