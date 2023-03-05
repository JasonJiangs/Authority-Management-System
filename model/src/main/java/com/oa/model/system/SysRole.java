package com.oa.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.oa.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


@Data
@ApiModel(description = "role")
@TableName("sys_role")
public class SysRole extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "Role name")
	@TableField("role_name")
	private String roleName;

	@ApiModelProperty(value = "Role code")
	@TableField("role_code")
	private String roleCode;

	@ApiModelProperty(value = "Description")
	@TableField("description")
	private String description;

}

