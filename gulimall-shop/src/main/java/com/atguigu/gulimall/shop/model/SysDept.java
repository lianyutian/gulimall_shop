package com.atguigu.gulimall.shop.model;

import lombok.Getter;
import lombok.Setter;

import java.sql.Date;
import java.util.List;

/**
 * 部门模型
 *
 * @author lm
 * @since 2020/12/10 20:29
 */
@Getter
@Setter
public class SysDept {
    /**
     * 部门id
     */
    private String deptId;

    /**
     * 部门编码
     */
    private String deptNo;

    /**
     * 部门编号
     */
    private String deptName;

    /**
     * 上级部门id
     */
    private String pDeptId;

    /**
     * 上级部门名称
     */
    private String pDeptName;

    /**
     * 状态
     */
    private boolean status;

    /**
     * 部门管理者id
     */
    private String deptManagerId;

    private String managerName;

    private String phone;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 创建时间
     */
    private Date updateTime;

    /**
     * 删除标志
     */
    private boolean isDeleted;

    private List<SysDept> children;
}
