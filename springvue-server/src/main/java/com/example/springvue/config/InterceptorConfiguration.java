package com.example.springvue.config;

import com.example.springvue.interceptor.CorsInterceptor;
import com.example.springvue.interceptor.LoginInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 拦截器配置
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/9/23 11:34
 */
@Configuration
public class InterceptorConfiguration implements WebMvcConfigurer {
    @Autowired
    private CorsInterceptor corsInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // 跨域过滤器
        registry.addInterceptor(corsInterceptor).addPathPatterns("/**");
        registry.addInterceptor(loginInterceptor())
                .addPathPatterns("/**")
                .excludePathPatterns("/lucky/admin/login", "/lucky/login", "/lucky/register", "/lucky/upload/image",
                        "/lucky/good/list", "/lucky/user/info", "/image/**");
    }

    @Bean
    public LoginInterceptor loginInterceptor() {
        return new LoginInterceptor();
    }
}
