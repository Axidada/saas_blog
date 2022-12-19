package com.gt.common.shiro.session;


import org.apache.shiro.web.servlet.ShiroHttpServletRequest;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.apache.shiro.web.util.WebUtils;
import org.springframework.util.StringUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import java.io.Serializable;

public class CustomSessionManager extends DefaultWebSessionManager {
    /**
     * @description: 头部信息中具有sessionId
     * 请求头 Authorization : sessionId
     * 指定sessionId的获取方式
     * @params:
     * @return:
     * @author: Easy
     * @dateTime: 2021/5/26 17:54
     */
    protected Serializable getSessionId(ServletRequest request, ServletResponse response) {
        //获取请求头Authorization 中的数据
        String id = WebUtils.toHttp(request).getHeader("Authorization");
        if (StringUtils.isEmpty(id)) {
            //如果没有携带 则生成新的sessionId
            return super.getSessionId(request, response);
        } else {
            //我们的请求头 含有Easy
            id = id.replaceAll("Easy ", "");
            //返回sessionId
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_SOURCE, "header");
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID, id);
            request.setAttribute(ShiroHttpServletRequest.REFERENCED_SESSION_ID_IS_VALID, Boolean.TYPE);
            return id;
        }
    }
}
