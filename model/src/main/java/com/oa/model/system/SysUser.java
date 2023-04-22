package com.oa.model.system;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;

import com.oa.model.base.BaseEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel(description = "用户")
@TableName("sys_user")
public class SysUser extends BaseEntity {
	
	private static final long serialVersionUID = 1L;

	@ApiModelProperty(value = "username")
	@TableField("username")
	private String username;

	@ApiModelProperty(value = "password")
	@TableField("password")
	private String password;

	@ApiModelProperty(value = "name")
	@TableField("name")
	private String name;

	@ApiModelProperty(value = "phone number")
	@TableField("phone")
	private String phone;

	@ApiModelProperty(value = "url of head image")
	@TableField("head_url")
	private String headUrl;

	@ApiModelProperty(value = "dept id")
	@TableField("dept_id")
	private Long deptId;

	@ApiModelProperty(value = "post id")
	@TableField("post_id")
	private Long postId;

	@ApiModelProperty(value = "description")
	@TableField("description")
	private String description;

	@ApiModelProperty(value = "open id")
	@TableField("open_id")
	private String openId;

	@ApiModelProperty(value = "status 0:enable 1:disable")
	@TableField("status")
	private Integer status;

	@TableField(exist = false)
	private List<SysRole> roleList;
	//岗位
	@TableField(exist = false)
	private String postName;
	//部门
	@TableField(exist = false)
	private String deptName;
}

