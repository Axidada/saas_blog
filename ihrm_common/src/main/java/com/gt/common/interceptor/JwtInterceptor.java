package com.gt.common.interceptor;

import com.gt.common.entity.ResultCode;
import com.gt.common.exception.CommonException;
import com.gt.common.utils.JwtUtils;
import io.jsonwebtoken.Claims;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @description: 自定义拦截器
 * preHandle :进入到控制器方法之前执行的内容
 * boolean:
 * true:可以继续执行控制器的方法
 * false:拦截
 * postHandle:执行控制器方法之后执行的内容
 * afterCompletion: 响应结束之前执行的内容
 * <p>
 * 任务
 * 1.简化获取token数据的代码编写
 * 统一的用户权限校验(是否登录)
 * 2.判断用户是否具有访问当前接口的权限
 * @params:
 * @return:
 * @author: Easy
 * @dateTime: 2021/5/24 13:53
 */
@Component
public class JwtInterceptor extends HandlerInterceptorAdapter {

    /**
     * 简化获取token数据的代码编写
     *
     * @param request
     * @param response
     * @param handler
     * @return
     * @throws Exception
     */
    @Autowired
    private JwtUtils jwtUtils;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        //1.获取请求头信息：名称=Authorization
        String authorization = request.getHeader("Authorization");
        //2.判断请求头信息是否为空 或者是否已Easy开头
        if (!StringUtils.isEmpty(authorization) && authorization.startsWith("Easy")) {
            //获取token数据
            String token = authorization.replace("Easy", "");
            Claims claims = jwtUtils.parseJwt(token);

            if (claims != null) {
                //通过 claims 获取到当前用户的可访问Api权限字符串
                String apis = (String) claims.get("apis"); //api-user-delete pai-user-update
                //通过handler
                HandlerMethod h = (HandlerMethod) handler;
                //获取接口上的RequestMapping注解
                RequestMapping annotation = h.getMethodAnnotation(RequestMapping.class);
                //获取当前请求接口的name属性
                String name = annotation.name();
                if (apis.contains(name)) {
                    request.setAttribute("user_claims", claims);
                    return true;
                } else {
                    throw new CommonException(ResultCode.UNAUTHORISE);
                }
            }
        }
        throw new CommonException(ResultCode.UNAUTHENTICATED);
    }

}
