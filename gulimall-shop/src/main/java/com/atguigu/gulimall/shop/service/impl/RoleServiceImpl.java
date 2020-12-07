package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.dao.RoleDao;
import com.atguigu.gulimall.shop.model.PageInfo;
import com.atguigu.gulimall.shop.model.RoleReqForm;
import com.atguigu.gulimall.shop.model.SysRole;
import com.atguigu.gulimall.shop.service.RoleService;
import com.atguigu.gulimall.shop.utils.PageUtil;
import com.github.pagehelper.PageHelper;
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

    @Override
    public PageInfo getRoleList(RoleReqForm roleReqForm) {
        PageHelper.startPage(roleReqForm.getPageNum(), roleReqForm.getPageSize());
        List<SysRole> roleList = roleDao.getRoleList(roleReqForm);
        return PageUtil.getPageInfo(roleList);
    }
}
