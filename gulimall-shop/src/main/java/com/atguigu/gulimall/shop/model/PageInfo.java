package com.atguigu.gulimall.shop.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 分页模型
 *
 * @author lm
 * @since 2020-12-7
 */
@Getter
@Setter
public class PageInfo<T> {
    private Page page = new Page();
    /**
     * 数据列表
     */
    @ApiModelProperty(value = "数据列表")
    private List<T> dataList;
}
