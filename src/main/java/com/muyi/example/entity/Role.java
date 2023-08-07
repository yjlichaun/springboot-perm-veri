package com.muyi.example.entity;

import lombok.Data;

/**
 * @author 历川
 * @version 1.0
 * @description 角色类
 * @date 2023/8/7 10:56
 */
@Data
public class Role extends BaseEntity {
    
    /**
     * 角色id
     */
    private Integer id;
    
    /**
     * 角色名称
     */
    private String roleName;
}
