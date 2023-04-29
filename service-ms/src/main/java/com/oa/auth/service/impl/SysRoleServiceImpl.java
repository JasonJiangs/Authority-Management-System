package com.oa.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.oa.auth.mapper.SysRoleMapper;
import com.oa.auth.mapper.SysUserRoleMapper;
import com.oa.auth.service.SysRoleService;
import com.oa.auth.service.SysUserRoleService;
import com.oa.model.system.SysRole;
import com.oa.model.system.SysUserRole;
import com.oa.vo.system.AssginRoleVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @Author: Jason
 * @Date: 2023/3/6 12:52 12 52
 */

@Service
public class SysRoleServiceImpl extends ServiceImpl<SysRoleMapper, SysRole> implements SysRoleService {

    @Autowired
    private SysUserRoleService sysUserRoleService;

    @Autowired
    private SysUserRoleMapper sysUserRoleMapper;

//    @Transactional
    @Override
    public Map<String, Object> findRoleDataByUserId(Long userId) {
        // 1. query all roles, return list
        // baseMapper is already injected by ServiceImpl
        List<SysRole> allRolesList = this.list();

        // 2. query role-user relationship by id, query all role ids by user id
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
//        System.out.println("***********************userId: " + userId);
        wrapper.eq(SysUserRole::getUserId, userId);
        // role list for current user id
        List<SysUserRole> currentUserRoleList = sysUserRoleService.list(wrapper);
        // get role ids for current user id, use stream to get role id list *** this is a new feature in Java 8
        List<Long> currentUserRoleIdList =
                currentUserRoleList.stream().map(c -> c.getRoleId()).collect(Collectors.toList());

        // 3. loop role list, return role list when role id is in current user role id list
        List<SysRole> assignRoleList =
                allRolesList.stream().filter(item -> currentUserRoleIdList.contains(item.getId())).collect(Collectors.toList());

        // 4. return role map
        Map<String, Object> roleMap = new HashMap<>();
        roleMap.put("assignRoleList", assignRoleList);
        roleMap.put("allRoleList", allRolesList);
        return roleMap;

//        //查询所有的角色
//        List<SysRole> allRolesList = this.list();
//
//        //拥有的角色id
//        List<SysUserRole> existUserRoleList = sysUserRoleMapper.selectList(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, userId).select(SysUserRole::getRoleId));
//        List<Long> existRoleIdList = existUserRoleList.stream().map(c->c.getRoleId()).collect(Collectors.toList());
//
//        //对角色进行分类
//        List<SysRole> assginRoleList = new ArrayList<>();
//        for (SysRole role : allRolesList) {
//            //已分配
//            if(existRoleIdList.contains(role.getId())) {
//                assginRoleList.add(role);
//            }
//        }
//
//        Map<String, Object> roleMap = new HashMap<>();
//        roleMap.put("assginRoleList", assginRoleList);
//        roleMap.put("allRolesList", allRolesList);
//        return roleMap;
    }

    @Transactional
    @Override
    public void assignUserRoleRelationShip(AssginRoleVo assginRoleVo) {
        // 1. delete all role-user relationship by user id, step by step
//        sysUserRoleService.remove(new LambdaQueryWrapper<SysUserRole>().eq(SysUserRole::getUserId, assginRoleVo.getUserId()));
        LambdaQueryWrapper<SysUserRole> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(SysUserRole::getUserId, assginRoleVo.getUserId());
        sysUserRoleService.remove(wrapper);

        // 2. insert new role-user relationship
        List<Long> roleIdList = assginRoleVo.getRoleIdList();
        List<SysUserRole> sysUserRoleList = roleIdList.stream().map(item -> {
            SysUserRole sysUserRole = new SysUserRole();
            sysUserRole.setUserId(assginRoleVo.getUserId());
            sysUserRole.setRoleId(item);
            return sysUserRole;
        }).collect(Collectors.toList());
        sysUserRoleService.saveBatch(sysUserRoleList);
    }
}