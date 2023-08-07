package com.muyi.example.service;

import com.muyi.example.dto.LoginUser;
import com.muyi.example.util.R;

/**
 * @author 历川
 * @description 登录接口
 * @date 2023-08-07 13:00:39
 */

public interface LoginService {
    
    /**
     * 用户登录接口
     * @param loginUser 登录用户信息
     * @return R
     */
    R login(LoginUser loginUser);
    
    /**
     * 获取当前登录用户信息
     */
    R getInfo();
    
    /**
     * 登出
     * @return R
     */
    R logout();
}
