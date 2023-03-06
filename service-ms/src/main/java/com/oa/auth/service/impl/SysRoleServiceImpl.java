package com.oa.auth.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oa.auth.mapper.SysRoleMapper;
import com.oa.auth.service.SysRoleService;
import com.oa.model.system.SysRole;
import org.springframework.stereotype.Service;

/**
 * @Author: Jason
 * @Date: 2023/3/6 12:52 12 52
 */

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {
}