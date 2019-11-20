package com.example.springvue.util;


import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Description: 将时间转换存入数据库
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 23:11
 */
public class DateUtil {
    /**
     * 系统时间存入数据库进行转换
     *
     * @return
     */
    public static Date createTime() {
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss ");
        String time = sdf.format(date);
        Timestamp create = Timestamp.valueOf(time);
        return create;
    }

    /**
     * 数据库查询出的时间转换为系统时间
     *
     * @return
     */
    public static String createTimeVO(Date date) {
        if (date == null) {
            return "";
        } else {
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            return sdf.format(date);
        }
    }
}
