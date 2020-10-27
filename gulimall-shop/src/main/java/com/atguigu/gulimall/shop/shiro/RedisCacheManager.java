package com.atguigu.gulimall.shop.shiro;

import com.atguigu.gulimall.shop.utils.RedisUtil;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;
import org.apache.shiro.cache.CacheManager;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * shiro缓存管理器
 *
 * @author lm
 * @since 2020/10/27 20:40
 */
public class RedisCacheManager implements CacheManager {
    @Autowired
    private RedisUtil redisUtil;

    @Override
    public <K, V> Cache<K, V> getCache(String cacheName) throws CacheException {
        return new RedisCache<K, V>(redisUtil);
    }
}
