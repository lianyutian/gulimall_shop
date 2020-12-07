package com.atguigu.gulimall.shop.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 分页
 *
 * @author lm
 * @since 2020/12/7 19:57
 */
@Setter
@Getter
public class Page {
    /**
     * 总记录数
     */
    @ApiModelProperty(value = "总记录数")
    private Long totalRows;

    /**
     * 总页数
     */
    @ApiModelProperty(value = "总页数")
    private Integer totalPages;

    /**
     * 当前第几页
     */
    @ApiModelProperty(value = "当前第几页")
    private Integer pageNum;

    /**
     * 每页记录数
     */
    @ApiModelProperty(value = "每页记录数")
    private Integer pageSize;

    /**
     * 当前页记录数
     */
    @ApiModelProperty(value = "当前页记录数")
    private Integer curPageSize;
}
