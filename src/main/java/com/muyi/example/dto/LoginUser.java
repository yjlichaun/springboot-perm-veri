package com.muyi.example.dto;

import lombok.Data;

/**
 * @author 历川
 * @version 1.0
 * @description 登录用户类
 * @date 2023/8/7 13:04
 */
@Data
public class LoginUser {
    
    /**
     * 登录账号
     */
    private String username;
    
    /**
     * 登录密码
     */
    private String password;
}
