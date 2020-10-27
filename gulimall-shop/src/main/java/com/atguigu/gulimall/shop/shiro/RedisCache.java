package com.atguigu.gulimall.shop.shiro;

import com.alibaba.fastjson.JSON;
import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import com.atguigu.gulimall.shop.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.cache.Cache;
import org.apache.shiro.cache.CacheException;

import java.util.Collection;
import java.util.Set;
import java.util.concurrent.TimeUnit;

/**
 * redis缓存实现
 *
 * @author lm
 * @since 2020/10/27 20:43
 */
@Slf4j
public class RedisCache<K, V> implements Cache<K, V> {
    private RedisUtil redisUtil;

    /**
     * 用户鉴权key值
     */
    private String cacheKey = Constant.IDENTIFY_CACHE_KEY;

    public RedisCache(RedisUtil redisUtil) {
        this.redisUtil = redisUtil;
    }

    @Override
    public V get(K key) throws CacheException {
        log.info("从redis缓存中获取key对应数据[{}]", key);
        if (key == null) {
            return null;
        }
        try {
            String redisCacheKey = getCacheKey(key);
            Object value = redisUtil.get(redisCacheKey);
            if (value == null) {
                return null;
            }
            SimpleAuthorizationInfo simpleAuthorizationInfo = JSON.parseObject(value.toString(), SimpleAuthorizationInfo.class);
            return (V) simpleAuthorizationInfo;
        } catch (Exception exception) {
            throw new CacheException(exception);
        }
    }

    private String getCacheKey(K token) {
        if (null == token) {
            return null;
        } else {
            String userId = JWTUtil.getUserId(token.toString());
            return this.cacheKey + userId;
        }
    }

    @Override
    public V put(K key, V value) throws CacheException {
        log.info("put key [{}]", key);
        if (key == null) {
            log.warn("Saving a null key is meaningless, return value directly without call Redis.");
            return value;
        }
        // key值过期时间
        long expire = 24;
        try {
            String cacheKey = getCacheKey(key);
            redisUtil.set(cacheKey, value, expire, TimeUnit.HOURS);
            return value;
        } catch (Exception exception) {
            throw new CacheException(exception);
        }
    }

    @Override
    public V remove(K k) throws CacheException {
        return null;
    }

    @Override
    public void clear() throws CacheException {

    }

    @Override
    public int size() {
        return 0;
    }

    @Override
    public Set<K> keys() {
        return null;
    }

    @Override
    public Collection<V> values() {
        return null;
    }
}
