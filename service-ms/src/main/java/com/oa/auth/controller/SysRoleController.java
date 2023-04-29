package com.oa.auth.controller;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oa.auth.service.SysRoleService;
import com.oa.common.result.ResultCode;
import com.oa.model.system.SysRole;
import com.oa.vo.system.AssginRoleVo;
import com.oa.vo.system.SysRoleQueryVo;
import com.service.config.exception.TypeException;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * @Author: Jason
 * @Date: 2023/3/6 13:02 13 02
 */

@Api(tags = "Role Management API")
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

    @ApiOperation("Query all users information")  // test with http://localhost:8800/doc.html
    @GetMapping("findAll")
    public ResultCode findAll() {
        List<SysRole> roleList = sysRoleService.list();
        try {
            int a = 10/0;
        } catch (Exception e) {
            throw new TypeException(222, "Calculation failed.");
        }
        return ResultCode.succeed(roleList);
    }


    // conditional pagination query
    @ApiOperation("conditional pagination query")
    @GetMapping("{page}/{limit}")
    public ResultCode paginationQuery(@PathVariable Long page,
                                      @PathVariable Long limit,
                                      SysRoleQueryVo sysRoleQueryVo) {

        Page<SysRole> pageParam = new Page<>(page, limit);
        LambdaQueryWrapper<SysRole> wrapper = new LambdaQueryWrapper<>();
        String roleName = sysRoleQueryVo.getRoleName();
        if (!StringUtils.isEmpty(roleName)) {  // add fuzzy query condition
            wrapper.like(SysRole::getRoleName, roleName);
        }

        IPage<SysRole> pageModel = sysRoleService.page(pageParam, wrapper);
        return ResultCode.succeed(pageModel);
    }


    @ApiOperation(value = "add role")
    @PostMapping("save")
    public ResultCode save(@RequestBody @Validated SysRole role) {
        boolean is_success = sysRoleService.save(role);
        if (is_success) {
            return ResultCode.succeed();
        } else {
            return ResultCode.fail();
        }
    }

    @ApiOperation(value = "query by id")
    @GetMapping("get/{id}")
    public ResultCode get(@PathVariable Long id) {
        SysRole role = sysRoleService.getById(id);
        return ResultCode.succeed(role);
    }

    @ApiOperation(value = "update information")
    @PutMapping("update")
    public ResultCode updateById(@RequestBody SysRole role) {
        boolean is_success = sysRoleService.updateById(role);
        if (is_success) {
            return ResultCode.succeed();
        } else {
            return ResultCode.fail();
        }
    }

    @ApiOperation(value = "delete role")
    @DeleteMapping("remove/{id}")
    public ResultCode remove(@PathVariable Long id) {
        boolean is_success = sysRoleService.removeById(id);
        if (is_success) {
            return ResultCode.succeed();
        } else {
            return ResultCode.fail();
        }
    }

    @ApiOperation(value = "delete multiple roles")
    @DeleteMapping("batchRemove")
    public ResultCode batchRemove(@RequestBody List<Long> idList) {
        boolean is_success = sysRoleService.removeByIds(idList);
        if (is_success) {
            return ResultCode.succeed();
        } else {
            return ResultCode.fail();
        }
    }

    // query all user's role and assigned role
    @ApiOperation(value = "query all user's role and assigned role")
    @GetMapping("toAssign/{userId}")
    public ResultCode selectAssignRole(@PathVariable Long userId) {
        Map<String, Object> map = sysRoleService.findRoleDataByUserId(userId);
        return ResultCode.succeed(map);
    }

    // assign role to user
    @ApiOperation(value = "assign role to user")
    @PostMapping("doAssign")
    public ResultCode doAssign(@RequestBody AssginRoleVo assginRoleVo) {
        sysRoleService.assignUserRoleRelationShip(assginRoleVo);
        return ResultCode.succeed();
    }





}