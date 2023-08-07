package com.muyi.example.controller;

import cn.dev33.satoken.stp.StpUtil;
import com.muyi.example.dto.LoginUser;
import com.muyi.example.service.LoginService;
import com.muyi.example.util.R;
import com.muyi.example.util.constants.ErrorEnum;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 历川
 * @version 1.0
 * @description 登录相关Controller
 * @date 2023/8/7 12:59
 */
@RestController
@RequestMapping("/auth")
public class LoginController {
    
    @Autowired
    LoginService loginService;
    
    /**
     * 用户登录
     *
     * @param loginUser 登录信息
     */
    @PostMapping("/login")
    public R authLogin(@RequestBody LoginUser loginUser) {
        return loginService.login(loginUser);
    }
    
    /**
     * 获取当前登录用户的信息
     *
     * @return 用户登录信息
     */
    @PostMapping("/getInfo")
    public R getInfo() {
        boolean login = StpUtil.isLogin();
        if (!login) {
            return R.failed("请先登录再进行操作");
        }
        return loginService.getInfo();
    }
    
    
    
    @PostMapping("/logout")
    public R logout() {
        boolean login = StpUtil.isLogin();
        if (!login) {
            return R.failed("尚未登录,无法注销");
        }
        return loginService.logout();
    }
}
