package com.example.springvue.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * @Description: 资源映射配置
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/8/29 13:31
 */
@Configuration
public class CorsConfiguration implements WebMvcConfigurer {
    /**
     * 跨域配置
     *
     * @param registry
     */
/*    @Override
    public void addCorsMappings(CorsRegistry registry) {
        //对那种格式路径进行跨域处理
        registry.addMapping("/**")
                //允许的请求头
                .allowedHeaders("*")
                //允许的请求方法
                .allowedMethods("*")
                //支持的域
                .allowedOrigins("http://localhost:8080")
                //探测请求的有效期
                .maxAge(1800);
    }*/
    /**
     * 资源映射配置
     *
     * @param registry
     */
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/image/**")
                .addResourceLocations("file:C:/sch/Image/");
    }
}
