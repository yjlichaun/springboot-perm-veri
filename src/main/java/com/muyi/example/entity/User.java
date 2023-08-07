package com.muyi.example.entity;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.apache.ibatis.plugin.Interceptor;

/**
 * @author 历川
 * @version 1.0
 * @description 用户类
 * @date 2023/8/7 10:33
 */
@Data
public class User extends BaseEntity{
    
    private static final long serialVersionUID = 1L;
    
    /**
     * 用户id
     */
    private Integer userId;
    
    /**
     * 用户名称
     */
    private String username;
    
    /**
     * 用户密码
     */
    private String password;
    
    
    /**
     * 用户昵称
     */
    private String nickname;
}

























