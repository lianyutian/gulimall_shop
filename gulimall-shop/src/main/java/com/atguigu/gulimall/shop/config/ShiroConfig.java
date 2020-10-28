package com.atguigu.gulimall.shop.config;

import com.atguigu.gulimall.shop.shiro.*;
import org.apache.shiro.cache.CacheManager;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.servlet.Filter;
import java.util.LinkedHashMap;
import java.util.Map;

/**
 * shiro配置类
 *
 * @author lm
 * @since 2020/10/20 23:08
 */
@Configuration
public class ShiroConfig {
    @Bean
    public CacheManager cacheManager() {
        return new RedisCacheManager();
    }

    /**
     * 自定义token匹配器
     * 这里需要添加@Bean将 匹配器交给Spring容器管理
     * 不然在匹配器中注入redisUtil为null
     *
     * @return token匹配器
     */
    @Bean
    public CustomCredentialsMatcher customCredentialsMatcher() {
        return new CustomCredentialsMatcher();
    }

    /**
     * 获取shiroFilter 负责拦截所有请求
     *
     * @param securityManager 安全管理器
     * @return shiroFilter
     */
    @Bean
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置过滤器
        Map<String, Filter> filterMap = new LinkedHashMap<>();
        filterMap.put("token", new CustomAccessControlFilter());
        shiroFilterFactoryBean.setFilters(filterMap);

        Map<String, String> hashMap = new LinkedHashMap<>();
        // 开放认证配置
        hashMap.put("/user/login", "anon");
        // 需要认证配置(配置自定义的过滤器)
        hashMap.put("/**", "token");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(hashMap);
        return shiroFilterFactoryBean;
    }

    @Bean
    public SecurityManager getSecurityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(getRealm());
        return securityManager;
    }

    @Bean
    public Realm getRealm() {
        CustomRealm customRealm = new CustomRealm();
        customRealm.setCredentialsMatcher(customCredentialsMatcher());

        // 开启缓存
        customRealm.setCacheManager(cacheManager());
        return customRealm;
    }

    /**
     * 注解配置
     *
     * @param securityManager 安全管理器
     * @return
     */
    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(SecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor advisor = new AuthorizationAttributeSourceAdvisor();
        advisor.setSecurityManager(securityManager);
        return advisor;
    }
}
