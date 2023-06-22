package com.oa.auth.controller;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.oa.auth.service.SysUserService;
import com.oa.common.result.ResultCode;
import com.oa.model.system.SysUser;
import com.oa.vo.system.SysUserQueryVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * user table 前端控制器
 * </p>
 *
 * @author SYJ
 * @since 2023-04-22
 */
@Api(tags = "user management API")
@RestController
@RequestMapping("/admin/system/sysUser")
public class SysUserController {

    @Autowired
    private SysUserService sysUserService;

    // user conditional query with pagination
    @ApiOperation(value = "user conditional query with pagination")
    @GetMapping("{page}/{limit}")
    public ResultCode paginationQuery(@PathVariable Long page,
                                      @PathVariable Long limit,
                                      SysUserQueryVo sysUserQueryVo) {
        // create a page object
        Page<SysUser> pageParam = new Page<>(page, limit);
        // get encapsulated query conditions
        LambdaQueryWrapper<SysUser> wrapper = new LambdaQueryWrapper<>();
        String createTimeBegin = sysUserQueryVo.getCreateTimeBegin();
        String createTimeEnd = sysUserQueryVo.getCreateTimeEnd();
        String username = sysUserQueryVo.getKeyword();
        if (!StringUtils.isEmpty(username)) {
            wrapper.like(SysUser::getUsername, username);
        }
        if (!StringUtils.isEmpty(createTimeBegin)) {
            wrapper.ge(SysUser::getCreateTime, createTimeBegin);
        }
        if (!StringUtils.isEmpty(createTimeEnd)) {
            wrapper.le(SysUser::getCreateTime, createTimeEnd);
        }
        // call the method to query
        IPage<SysUser> sysUserPage = sysUserService.page(pageParam, wrapper);
        return ResultCode.succeed(sysUserPage);
    }

    // get user by id
    @ApiOperation(value = "get user by id")
    @GetMapping("get/{id}")
    public ResultCode getSysUserById(@PathVariable Long id) {
        SysUser sysUser = sysUserService.getById(id);
        return ResultCode.succeed(sysUser);
    }

    // save user
    @ApiOperation(value = "save user")
    @PostMapping("save")
    public ResultCode saveSysUser(@RequestBody SysUser sysUser) {
        sysUserService.save(sysUser);
        return ResultCode.succeed();
    }

    // update user
    // @RequestBody cannot be used with GET method
    @ApiOperation(value = "update user")
    @PutMapping("update")
    public ResultCode updateSysUserById(@RequestBody SysUser sysUser) {
        sysUserService.updateById(sysUser);
        return ResultCode.succeed();
    }

    // delete user by id
    @ApiOperation(value = "delete user by id")
    @DeleteMapping("remove/{id}")
    public ResultCode removeSysUserById(@PathVariable Long id) {
        sysUserService.removeById(id);
        return ResultCode.succeed();
    }

    // update user status
    @ApiOperation(value = "update user status")
    @GetMapping("updateStatus/{id}/{status}")
    public ResultCode updateUserById(@PathVariable Long id,
                                      @PathVariable Integer status) {
        sysUserService.updateStatus(id, status);
        return ResultCode.succeed();
    }

}

