package com.atguigu.gulimall.shop.dao;

import com.atguigu.gulimall.shop.model.SysDept;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * 部门管理dao
 *
 * @author lm
 * @since 2020/12/10 20:36
 */
@Mapper
public interface DeptDao {
    List<SysDept> getAllDeptList();
}
