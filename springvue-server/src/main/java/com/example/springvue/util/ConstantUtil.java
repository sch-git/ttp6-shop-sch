package com.example.springvue.util;

/**
 * @Description: TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/9 10:51
 */
public class ConstantUtil {
    /**
     * 统一成功
     */
    private final static Integer SUCCESS_CODE = 200;
    /**
     * 统一失败
     */
    private final static Integer ERROR_CODE = 404;

    /**
     * 用户状态 0：无效 1：有效 默认：1
     */
    private final static Integer[] USER_STATUS = {0, 1};

    /**
     * 商品状态 0：下架 1：正常
     */
    private final static Integer[] GOOD_STATUS = {0, 1};

    /**
     * 订单状态 0：已取消 1：待支付 2：已完成
     */
    private final static Integer[] ORDER_STATUS = {0, 1, 2};

    /**
     * 分类状态 0：无效 1：有效
     */
    private final static Integer[] CATEGORY_STATUS = {0, 1};
}
