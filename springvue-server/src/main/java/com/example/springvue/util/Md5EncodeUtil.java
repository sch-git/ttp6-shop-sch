package com.example.springvue.util;

import org.springframework.util.DigestUtils;

/**
 * @Description: MD5加密
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/16 22:48
 */
public class Md5EncodeUtil {

    /**
     * MD5加密
     *
     * @param data
     * @return
     */
    public static String encode(String data) {
        return DigestUtils.md5DigestAsHex(data.getBytes());
    }

    /**
     * 生成密码盐
     *
     * @return
     */
    public static Byte setSalt() {
        /*密码盐0-9*/
        return (byte) (Math.random() * (10));
    }
}
