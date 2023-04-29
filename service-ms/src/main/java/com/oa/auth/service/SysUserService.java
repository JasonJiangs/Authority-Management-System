package com.oa.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oa.model.system.SysUser;

/**
 * <p>
 * user table 服务类
 * </p>
 *
 * @author SYJ
 * @since 2023-04-22
 */
public interface SysUserService extends IService<SysUser> {

    void updateSatus(Long id, Integer status);
}
