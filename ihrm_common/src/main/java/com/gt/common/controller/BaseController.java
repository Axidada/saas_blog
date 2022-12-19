package com.gt.common.controller;

import com.gt.domain.system.response.ProfileResult;
import io.jsonwebtoken.Claims;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.subject.PrincipalCollection;
import org.apache.shiro.subject.Subject;
import org.springframework.web.bind.annotation.ModelAttribute;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class BaseController {


    public HttpServletRequest request;
    public HttpServletResponse response;
    protected String companyId;
    protected String companyName;
    protected String userId;
    protected Claims claims;

    //使用shiro获取
    //进入控制器之前执行的方法
    //使用jwt方式获取
//        @ModelAttribute
//        public void setResAndReq(HttpServletRequest request,HttpServletResponse response){
//            this.request = request;
//            this.response = response;
//            Object obj = request.getAttribute("user_claims");
//            if (obj != null){
//                this.claims = (Claims) obj;
//                this.companyId = (String) claims.get("companyId");
//                this.companyName= (String) claims.get("companyName");
//            }
//        }
    @ModelAttribute
    public void setResAndReq(HttpServletRequest request, HttpServletResponse response) {
        this.request = request;
        this.response = response;

        //获取session 中的安全数据
        Subject subject = SecurityUtils.getSubject();
        
        //subject 获取所有的安全数据集合
        PrincipalCollection principals = subject.getPrincipals();
        if (principals != null && !principals.isEmpty()) {
            //获取安全数据
            ProfileResult result = (ProfileResult) principals.getPrimaryPrincipal();
            this.companyId = result.getCompanyId();
            this.companyName = result.getCompany();
            this.userId=result.getUserId();
        }
    }
}


