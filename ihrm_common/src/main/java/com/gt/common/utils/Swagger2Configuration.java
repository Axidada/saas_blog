package com.gt.common.utils;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.ApiInfoBuilder;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.service.ApiInfo;
import springfox.documentation.service.Contact;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@Configuration
@EnableSwagger2
public class Swagger2Configuration {
    @Bean
    public Docket petApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                .groupName("后端接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gt.company")) //指定提供接口所在的基包
                .build();
    }

    @Bean
    public Docket adminApi() {
        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiAdminInfo())
                .groupName("后台接口")
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.gt.system")) //指定提供接口所在的基包
                .build();
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     *
     * @return
     */
    private ApiInfo apiAdminInfo() {
        return new ApiInfoBuilder()
                .title("后台接口")
                .contact(new Contact("allen", "null", "name@example.com"))
                .version("0.1")
                .termsOfServiceUrl("localhost:9001/")
                .description("demo api")
                .build();
    }

    /**
     * 该套 API 说明，包含作者、简介、版本、host、服务URL
     *
     * @return
     */
    private ApiInfo apiInfo() {
        return new ApiInfoBuilder()
                .title("后端接口")
                .contact(new Contact("allen", "null", "name@example.com"))
                .version("0.1")
                .termsOfServiceUrl("localhost:9002/")
                .description("demo api")
                .build();
    }


//    @Bean
//    public Docket buildDocket(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(buildApiInf())
//                .groupName("公司模块")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gt.company"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//    @Bean
//    public Docket buildDocket2(){
//        return new Docket(DocumentationType.SWAGGER_2)
//                .apiInfo(buildApiInf())
//                .groupName("系统模块")
//                .select()
//                .apis(RequestHandlerSelectors.basePackage("com.gt.system"))
//                .paths(PathSelectors.any())
//                .build();
//    }
//
//    private ApiInfo buildApiInf(){
//        return new ApiInfoBuilder()
//                .title("Saas微服务接口文档")
//                .description("springboot swagger2")
//                .termsOfServiceUrl("https://www.yanxias.com")
//                .contact(new Contact("yanxias", "https://www.yanxias.com", "1213036993@qq.com"))
//                .license("")
//                .licenseUrl("")
//                .version("1.0.0")
//                .build();
//
//    }

}
