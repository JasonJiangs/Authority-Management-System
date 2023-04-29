package com.oa.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.oa.model.system.SysRole;
import com.oa.vo.system.AssginRoleVo;

import java.util.Map;

/**
 * @Author: Jason
 * @Date: 2023/3/6 12:51 12 51
 */
public interface SysRoleService extends IService<SysRole> {

    // find role data by user id
    Map<String, Object> findRoleDataByUserId(Long userId);

    // assign role to user
    void assignUserRoleRelationShip(AssginRoleVo assginRoleVo);
}
