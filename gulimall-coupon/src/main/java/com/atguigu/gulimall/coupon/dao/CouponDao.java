package com.atguigu.gulimall.coupon.dao;

import com.atguigu.gulimall.coupon.entity.CouponEntity;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;

/**
 * 优惠券信息
 * 
 * @author liming
 * @email 1036674967@gmail.com
 * @date 2020-08-25 23:10:11
 */
@Mapper
public interface CouponDao extends BaseMapper<CouponEntity> {
	
}
