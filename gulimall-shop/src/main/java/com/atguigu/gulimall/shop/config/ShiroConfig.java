package com.atguigu.gulimall.shop.config;

import com.atguigu.gulimall.shop.shiro.CustomRealm;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.realm.Realm;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

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
    /**
     * 获取shiroFilter 负责拦截所有请求
     *
     * @param securityManager 安全管理器
     * @return shiroFilter
     */
    @Bean("shiroFilter")
    public ShiroFilterFactoryBean getShiroFilterFactoryBean(SecurityManager securityManager) {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);
        // 配置系统受限资源
        Map<String, String> filterMap = new LinkedHashMap<>();
        filterMap.put("/login", "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterMap);
        return shiroFilterFactoryBean;
    }

    @Bean("securityManager")
    public SecurityManager getSecurityManager(Realm realm) {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(realm);
        return securityManager;
    }

    @Bean("realm")
    public Realm getRealm() {
        return new CustomRealm();
    }
}
