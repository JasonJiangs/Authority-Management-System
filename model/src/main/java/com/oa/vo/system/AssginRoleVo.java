package com.oa.vo.system;


import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel(description = "assign role to user")
@Data
public class AssginRoleVo {

    @ApiModelProperty(value = "user id")
    private Long userId;

    @ApiModelProperty(value = "role id list")
    private List<Long> roleIdList;

}
