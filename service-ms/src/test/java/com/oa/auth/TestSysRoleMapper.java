package com.oa.auth;

import com.oa.auth.mapper.SysRoleMapper;
import com.oa.model.system.SysRole;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import javax.annotation.Resource;
import java.util.Arrays;
import java.util.List;

/**
 * @Author: Jason
 * @Date: 2023/3/4 21:34 21 34
 */

@SpringBootTest
public class TestSysRoleMapper {


    // injection
    @Autowired
    private SysRoleMapper mapper;

    @Test
    public void getAll() {
        List<SysRole> list = mapper.selectList(null);
        System.out.println(list);
    }

    @Test
    public void insert() {
        SysRole sysRole = new SysRole();
        sysRole.setRoleCode("Role Administrator");
        sysRole.setRoleCode("role");
        sysRole.setDescription("role manager");

        int rows = mapper.insert(sysRole);
        System.out.println(rows);
        System.out.println(sysRole);
    }

    @Test
    public void update() {
        SysRole sysRole = mapper.selectById(9);
        sysRole.setRoleName("updateRoleAdmin");
        int rows = mapper.updateById(sysRole);
        System.out.println(rows);
    }

    @Test
    public void deleteId() {
        int row = mapper.deleteById(9);
        System.out.println(row);
    }

    @Test
    public void deleteBatch() {
        int i = mapper.deleteBatchIds(Arrays.asList(1, 2));
        System.out.println(i);
    }

}