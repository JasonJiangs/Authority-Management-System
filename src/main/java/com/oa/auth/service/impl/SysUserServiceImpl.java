package com.oa.auth.service.impl;

import com.oa.auth.entity.SysUser;
import com.oa.auth.mapper.SysUserMapper;
import com.oa.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * user table 服务实现类
 * </p>
 *
 * @author SYJ
 * @since 2023-04-22
 */
@Service
public class SysUserServiceImpl extends ServiceImpl<SysUserMapper, SysUser> implements SysUserService {

}
