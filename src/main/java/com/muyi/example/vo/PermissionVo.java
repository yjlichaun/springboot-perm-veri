package com.muyi.example.vo;

import com.muyi.example.dto.Permission;
import lombok.Data;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description 权限Vo类
 * @date 2023/8/8 10:46
 */
@Data
public class PermissionVo {
    
    private String menuName;
    
    private List<Permission> permissions;
}
