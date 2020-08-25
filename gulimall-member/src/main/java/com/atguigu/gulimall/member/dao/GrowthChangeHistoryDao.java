package com.atguigu.gulimall.member.dao;

import com.atguigu.gulimall.member.entity.GrowthChangeHistoryEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 成长值变化历史记录
 * 
 * @author liming
 * @email 1036674967@gmail.com
 * @date 2020-08-25 23:27:33
 */
@Mapper
public interface GrowthChangeHistoryDao extends BaseMapper<GrowthChangeHistoryEntity> {
	
}
