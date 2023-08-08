package com.muyi.example.dto;

import lombok.Data;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description 权限菜单类
 * @date 2023/8/8 10:10
 */
@Data
public class Menu {
    
    private String menuCode;
    
    private String menuName;
    
    private List<Permission> permissions;
}
