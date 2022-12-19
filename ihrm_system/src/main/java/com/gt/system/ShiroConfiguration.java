package com.gt.system;

import com.gt.common.shiro.realm.IhrmRealm;
import com.gt.common.shiro.session.CustomSessionManager;
import com.gt.system.shiro.realm.UserRealm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.web.session.mgt.DefaultWebSessionManager;
import org.crazycake.shiro.RedisCacheManager;
import org.crazycake.shiro.RedisManager;
import org.crazycake.shiro.RedisSessionDAO;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class ShiroConfiguration {

    @Value("${spring.redis.host}")
    private String host;

    @Value("${spring.redis.port}")
    private int port;

    //1.配置自定义的Realm
    @Bean
    public IhrmRealm getRealm() {
        return new UserRealm();
    }

    //2.配置安全管理器
    @Bean
    public SecurityManager securityManager(IhrmRealm realm) {
        //使用默认的安全管理器
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager(realm);
        // 自定义session管理 使用redis
        securityManager.setSessionManager(sessionManager());
        // 自定义缓存实现 使用redis
        securityManager.setCacheManager(cacheManager());
        //将自定义的realm交给安全管理器统一调度管理
        securityManager.setRealm(realm);
        return securityManager;
    }

    //3.Filter工厂，设置对应的过滤条件和跳转条件

    /**
     * @description: TODO
     * @params: [securityManager]
     * @return: org.apache.shiro.spring.web.ShiroFilterFactoryBean
     * @author: Easy
     * @dateTime: 2021/6/25 16:42
     * <p>
     * 在Web程序中 shiro进行权限控制全部是通过一组过滤器集合进行控制
     */

    @Bean
    public ShiroFilterFactoryBean shirFilter(SecurityManager securityManager) {
        //1.创建shiro过滤器工厂
        ShiroFilterFactoryBean filterFactory = new ShiroFilterFactoryBean();
        //2.设置安全管理器
        filterFactory.setSecurityManager(securityManager);
        //3.通用配置（配置登录页面，登录成功页面，验证未成功页面）
        filterFactory.setLoginUrl("/autherror?code=1"); //设置登录页面
        filterFactory.setUnauthorizedUrl("/autherror?code=2"); //授权失败跳转页面
        //4.配置过滤器集合
        /**
         * key  ：访问连接
         *      支持通配符的形式
         * value：过滤器类型
         *      shiro常用过滤器
         *          anno    ：匿名访问（表明此链接所有人可以访问）
         *          authc   ：认证后访问（表明此链接需登录认证成功之后可以访问）
         */
        Map<String, String> filterMap = new LinkedHashMap<String, String>();
        //4.配置请求连接过滤器配置
        //匿名访问（所有人员可以使用）
        filterMap.put("/system/login", "anon");
        filterMap.put("/autherror", "anon");
        //具有指定权限访问
        //filterMap.put("/user/find", "perms[user-find]");
        //认证之后访问（登录之后可以访问）
        filterMap.put("/**", "authc");
        //具有指定角色可以访问
//        filterMap.put("/user/**", "roles[系统管理员]");
        //5.设置过滤器
        filterFactory.setFilterChainDefinitionMap(filterMap);
        return filterFactory;
    }

    //配置shiro注解支持
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }

    /**
     * @description: 配置shiro redisManager
     * @params: redis控制器 操作redis
     * @return: org.crazycake.shiro.RedisManager
     * @author: Easy
     * @dateTime: 2021/6/25 16:44
     */
    @Bean
    public RedisManager redisManager() {
        RedisManager redisManager = new RedisManager();
        redisManager.setHost(host);
        redisManager.setPort(port);
        return redisManager;
    }

    //cacheManager缓存 redis实现
    @Bean
    public RedisCacheManager cacheManager() {
        RedisCacheManager redisCacheManager = new RedisCacheManager();
        redisCacheManager.setRedisManager(redisManager());
        return redisCacheManager;
    }

    /**
     * RedisSessionDAO shiro sessionDao层的实现 通过redis
     * 使用的是shiro-redis开源插件
     */
    @Bean
    public RedisSessionDAO redisSessionDAO() {
        RedisSessionDAO redisSessionDAO = new RedisSessionDAO();
        redisSessionDAO.setRedisManager(redisManager());
        return redisSessionDAO;
    }

    /**
     * @description: 会话管理器
     * @params: shiro session的管理
     * @return:
     * @author: Easy
     * @dateTime: 2021/6/25 16:45
     */
    @Bean
    public DefaultWebSessionManager sessionManager() {
        CustomSessionManager sessionManager = new CustomSessionManager();
        sessionManager.setSessionDAO(redisSessionDAO());
        //禁用 cookie
        sessionManager.setSessionIdCookieEnabled(false);
        //禁用 url重写 url;jsessionid=id
        sessionManager.setSessionIdUrlRewritingEnabled(false);
//        Cookie cookie =  sessionManager.getSessionIdCookie();
//        cookie.setName("my_sid"); //声明cooike中session的名称
//        sessionManager.setSessionIdCookie(cookie);
        return sessionManager;
    }
}
