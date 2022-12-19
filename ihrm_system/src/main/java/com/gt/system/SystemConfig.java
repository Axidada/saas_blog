package com.gt.system;

import com.gt.common.interceptor.JwtInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;


//@Configuration
public class SystemConfig extends WebMvcConfigurationSupport {
    @Autowired
    private JwtInterceptor jwtInterceptor;

    /**
     * @description: TODO
     * @params: 添加拦截器的配置
     * @return:
     * @author: Easy
     * @dateTime: 2021/5/24 14:19
     */
    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        //1.添加自定义拦截器
        registry.addInterceptor(jwtInterceptor).
                addPathPatterns("/**").//2.指定拦截器的url地址
                excludePathPatterns("/system/login", "/frame/register/**");//指定不拦截的url地址
    }
}
