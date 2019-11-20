package com.example.springvue.util;

/**
 * @Description: token TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 15:44
 */
public class TokenUtil {
    private static String token = null;
    private static String role = null;

    public static String getToken() {
        return token;
    }

    public static void setToken(String newtoken) {
        token = newtoken;
    }

    public static String getRole() {
        return role;
    }

    public static void setRole(String newrole) {
        role = newrole;
    }
}
