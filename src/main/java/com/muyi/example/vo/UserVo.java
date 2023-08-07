package com.muyi.example.vo;

import com.muyi.example.entity.BaseEntity;
import com.muyi.example.entity.Role;
import com.muyi.example.entity.User;
import lombok.Data;

import java.util.Set;

/**
 * @author 历川
 * @version 1.0
 * @description 用户传入类
 * @date 2023/8/7 18:08
 */
@Data
public class UserVo extends User {
    
    private Set<Role> roles;
}
