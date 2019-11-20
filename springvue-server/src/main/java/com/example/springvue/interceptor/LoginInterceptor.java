package com.example.springvue.interceptor;

import org.springframework.web.servlet.HandlerInterceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * @Description: 登录拦截器 TODO
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 11:25
 */
public class LoginInterceptor implements HandlerInterceptor {
    private static String METHOD = "OPTIONS";
    private static String adminStr = "admin";

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        if (METHOD.equals(request.getMethod())) {
            return true;
        }
        HttpSession session = request.getSession();
        System.out.println(session.getId());
        if (session.getAttribute(session.getId()) != null || session.getAttribute(session.getId() + adminStr) != null) {
            return true;
        }
        return false;
    }
}
