package com.atguigu.gulimall.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 角色关联权限
 *
 * @author lm
 * @since 2020/12/8 22:53
 */
@Getter
@Setter
public class SysRolePermission {
    /**
     * id
     */
    private String id;

    /**
     * 角色id
     */
    private String roleId;

    /**
     * 权限id
     */
    private String permissionId;

    /**
     * 创建时间
     */
    private Date createTime;
}
