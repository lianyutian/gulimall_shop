package com.atguigu.gulimall.shop.service.impl;

import com.atguigu.gulimall.shop.common.exception.GuliException;
import com.atguigu.gulimall.shop.common.exception.ResponseCode;
import com.atguigu.gulimall.shop.constants.Constant;
import com.atguigu.gulimall.shop.dao.UserDao;
import com.atguigu.gulimall.shop.model.LoginForm;
import com.atguigu.gulimall.shop.model.SysUser;
import com.atguigu.gulimall.shop.service.PermissionService;
import com.atguigu.gulimall.shop.service.RoleService;
import com.atguigu.gulimall.shop.service.UserService;
import com.atguigu.gulimall.shop.utils.JWTUtil;
import com.atguigu.gulimall.shop.utils.PasswordUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * UserService实现类
 *
 * @author lm
 * @since 2020/10/21 23:21
 */
@Service
public class UserServiceImpl implements UserService {
    /**
     * 登录相关dao
     */
    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @Override
    public String login(LoginForm loginForm) {
        SysUser user = userDao.getUserByUserName(loginForm.getUserName());
        if (user == null) {
            throw new GuliException(ResponseCode.USERNAME_OR_PASSWORD_ERR);
        }
        // 密码错误
        if (!PasswordUtil.matches(user.getSalt(), loginForm.getPassWord(), user.getPassWord())) {
            throw new GuliException(ResponseCode.USERNAME_OR_PASSWORD_ERR);
        }
        if (user.getStatus() == 2) {
            throw new GuliException(ResponseCode.ACCOUNT_LOCK_TIP);
        }
        Map<String, Object> claims = new HashMap<>(16);
        // 设置token携带信息
        claims.put(Constant.ROLES_INFOS_KEY, getUserRolesByUserId(user.getId()));
        claims.put(Constant.PERMISSIONS_INFOS_KEY, getUserPermsByUserId(user.getId()));
        return JWTUtil.getToken(user.getId(), claims);
    }

    /**
     * 根据用户ID查询用户角色信息
     *
     *
     * @param userId 用户ID
     * @return 角色集合
     */
    private List<String> getUserRolesByUserId(String userId) {
        return roleService.getUserRolesByUserId(userId);
    }

    /**
     * 根据用户ID查询用户权限信息
     *
     * @param userId 用户ID
     * @return 权限集合
     */
    private List<String> getUserPermsByUserId(String userId) {
        return permissionService.getUserPermsByUserId(userId);
    }
}
