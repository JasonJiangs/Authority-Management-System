package com.oa.auth.controller;

import com.oa.common.result.ResultCode;
import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: Jason
 * @Date: 2023/3/19 14:09 14 09
 */

@Api(tags = "Login Module")
@RestController
@RequestMapping("/admin/system/index")
public class IndexController {

    // login
    @PostMapping("login")
    public ResultCode login() {
        Map<String, Object> map = new HashMap<>();
        map.put("token", "admin-token");
        return ResultCode.succeed(map);
    }

    //info
    @GetMapping("info")
    public ResultCode info() {
        Map<String, Object> map = new HashMap<>();
        map.put("roles","[admin]");
        map.put("name","admin");
        map.put("avatar","https://oss.aliyuncs.com/aliyun_id_photo_bucket/default_handsome.jpg");
        return ResultCode.succeed(map);
    }

    @PostMapping("logout")
    public ResultCode logout(){
        return ResultCode.succeed();
    }
}