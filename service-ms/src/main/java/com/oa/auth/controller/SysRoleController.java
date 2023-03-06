package com.oa.auth.controller;

import com.oa.auth.service.SysRoleService;
import com.oa.common.result.ResultCode;
import com.oa.model.system.SysRole;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author: Jason
 * @Date: 2023/3/6 13:02 13 02
 */

@RestController
@RequestMapping("/admin/system/sysRole")
public class SysRoleController {

    @Autowired
    private SysRoleService sysRoleService;

    // http://localhost:8800/admin/system/sysRole/findAll
//    @GetMapping("findAll")
//    public List<SysRole> findAll() {
//        List<SysRole> roleList = sysRoleService.list();
//        return roleList;
//    }

    @GetMapping("findAll")
    public ResultCode findAll() {
        List<SysRole> roleList = sysRoleService.list();
        return ResultCode.succeed(roleList);
    }
}