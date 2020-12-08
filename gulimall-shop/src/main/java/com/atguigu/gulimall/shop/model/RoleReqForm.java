package com.atguigu.gulimall.shop.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * 角色界面请求表单
 *
 * @author lm
 * @since 2020/11/18 21:20
 */
@Getter
@Setter
public class RoleReqForm {
    @ApiModelProperty(value = "第几页")
    private int pageNum = 1;
    @ApiModelProperty(value = "当前页的数量")
    private int pageSize;
    @ApiModelProperty(value = "角色id")
    private String roleId;
    @ApiModelProperty(value = "角色名称")
    private String roleName;
    @ApiModelProperty(value = "角色状态")
    private Boolean status;
    @ApiModelProperty(value = "开始时间")
    private String createTime;
    @ApiModelProperty(value = "结束时间")
    private String endTime;
}
