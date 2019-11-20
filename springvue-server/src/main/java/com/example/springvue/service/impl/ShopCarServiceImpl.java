package com.example.springvue.service.impl;

import com.example.springvue.pojo.dto.CarItemDTO;
import com.example.springvue.pojo.vo.CarItemVO;
import com.example.springvue.service.ShopCarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/26 13:48
 */
@Service
public class ShopCarServiceImpl implements ShopCarService {
    @Autowired
    private HttpSession session;

    /**
     * 添加商品到购物车
     *
     * @param carItemDTO
     */
    @Override
    public void addCarItem(CarItemDTO carItemDTO) {
        List<CarItemVO> carItemVOList = (List<CarItemVO>) session.getAttribute(session.getId() + "car");
        CarItemVO carItemVO = new CarItemVO();
        carItemVO.setDTO(carItemDTO);
        if (carItemVOList.size() > 0) {
            // 判断该商品是否已在购物车中
            for (CarItemVO oldCarItemVO : carItemVOList) {
                if (oldCarItemVO.getId().equals(carItemVO.getId())) {
                    oldCarItemVO.setNumber(oldCarItemVO.getNumber() + carItemVO.getNumber());
                    return;
                }
            }
            carItemVOList.add(carItemVO);
        } else {

            carItemVOList.add(carItemVO);
        }
    }

    /**
     * 从购物车中删除商品
     *
     * @param id 商品id
     */
    @Override
    public void deleteCarItem(Long id) {
        List<CarItemVO> carItemVOList = (List<CarItemVO>) session.getAttribute(session.getId() + "car");
        for (CarItemVO oldCarItemVO : carItemVOList) {
            if (oldCarItemVO.getId().equals(id)) {
                carItemVOList.remove(oldCarItemVO);
                return;
            }
        }
    }

    /**
     * 修改购物车中商品数量
     *
     * @param id     商品id
     * @param number 商品数量
     */
    @Override
    public void updateCarItemNumber(Long id, Integer number) {
        List<CarItemVO> carItemVOList = (List<CarItemVO>) session.getAttribute(session.getId() + "car");
        for (CarItemVO oldCarItemVO : carItemVOList) {
            if (oldCarItemVO.getId().equals(id)) {
                oldCarItemVO.setNumber(number);
                return;
            }
        }
    }
}
