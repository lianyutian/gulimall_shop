package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.dao.RoleDao;
import com.atguigu.gulimall.shop.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * RoleService实现类
 *
 * @author lm
 * @since 2020/10/27 22:22
 */
@Service
public class RoleServiceImpl implements RoleService {
    @Autowired
    private RoleDao roleDao;

    @Override
    public List<String> getUserRolesByUserId(String userId) {
        List<String> roleIds = getRoleIdsByUserId(userId);
        if (CollectionUtils.isEmpty(roleIds)) {
            return null;
        }
        return roleDao.getRolesByRoleIds(roleIds);
    }

    @Override
    public List<String> getRoleIdsByUserId(String userId) {
        return roleDao.getRoleIdsByUserId(userId);
    }
}
