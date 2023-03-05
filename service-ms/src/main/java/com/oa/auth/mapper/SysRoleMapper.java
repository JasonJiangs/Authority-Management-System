package com.oa.auth.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.oa.model.system.SysRole;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

/**
 * @Author: Jason
 * @Date: 2023/3/4 21:31 21 31
 */

@Repository
//@Mapper
public interface SysRoleMapper extends BaseMapper<SysRole> {
}