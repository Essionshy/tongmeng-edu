package com.tingyu.tongmeng.edu.commons.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @Author essionshy
 * @Create 2020/10/25 20:17
 * @Version tongmeng-edu
 */

@Configuration
@EnableSwagger2
public class SwaggerConfiguration {

    @Bean
    public Docket createRestApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .groupName("webApi")
                .apiInfo(apiInfo())
                .select()
                //加了ApiOperation注解的类，才生成接口文档
              //  .apis(RequestHandlerSelectors.withMethodAnnotation(ApiOperation.class))
                //包下的类，才生成接口文档
                //.apis(RequestHandlerSelectors.basePackage("io.renren.controller"))
                .paths(PathSelectors.any())
                .build();
    }

    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("童蒙在线教育")
                .description("童蒙在线教育开发文档")
                .termsOfServiceUrl("https://github.com/Essionshy")
                .contact(new Contact("William Xiao","https://github.com/Essionshy","1218817610@qq.com"))
                .version("1.0.0")
                .build();
    }

}
