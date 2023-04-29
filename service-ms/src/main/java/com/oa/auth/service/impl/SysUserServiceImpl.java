package com.oa.auth.service.impl;

import com.oa.auth.mapper.SysUserMapper;
import com.oa.auth.service.SysUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oa.model.system.SysUser;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    private SysUserService sysUserService;

    @Override
    public void updateSatus(Long id, Integer status) {
        if (status != 0 && status != 1) {
            System.out.println("status is invalid: " + status);
            return;
        }
        SysUser sysUser = sysUserService.getById(id);
        sysUser.setStatus(status);
        sysUserService.updateById(sysUser);
    }
}
