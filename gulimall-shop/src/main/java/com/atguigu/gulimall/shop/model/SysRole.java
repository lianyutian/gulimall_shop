package com.atguigu.gulimall.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;

/**
 * 系统角色
 *
 * @author lm
 * @since 2020/11/18 21:23
 */
@Getter
@Setter
public class SysRole {
    /**
     * 角色id
     */
    private String id;

    /**
     * 角色名
     */
    private String name;

    /**
     * 角色描述
     */
    private String description;

    /**
     * 角色状态
     */
    private boolean status;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 是否删除
     */
    private boolean deleted;

    /**
     * 角色关联的系统权限
     */
    private List<SysPermission> permissionList;
}
