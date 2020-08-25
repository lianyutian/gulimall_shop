package com.atguigu.gulimall.order.dao;

import com.atguigu.gulimall.order.entity.OrderEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 订单
 * 
 * @author liming
 * @email 1036674967@gmail.com
 * @date 2020-08-25 23:32:29
 */
@Mapper
public interface OrderDao extends BaseMapper<OrderEntity> {
	
}
