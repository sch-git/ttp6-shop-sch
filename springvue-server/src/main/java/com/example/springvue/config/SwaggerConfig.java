package com.example.springvue.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Description: Swagger
 * @Author: chenghao.su@luckincoffee.com
 * @Date: 2019/8/29 13:38
 */
@EnableSwagger2
@Configuration
public class SwaggerConfig {
    @Bean
    public Docket controllerApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(new ApiInfoBuilder()
                        .title("SCH-SpringBoot-Vue")
                        .version("版本号：1.0")
                        .build())
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.example.springvue.controller"))
                .paths(PathSelectors.any())
                .build();
    }
}
