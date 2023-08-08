package com.muyi.example.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author 历川
 * @version 1.0
 * @description 权限类
 * @date 2023/8/8 10:10
 */

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Permission {
    
    private Integer permissionId;
    
    private String permissionName;
    
    private Integer requiredPerm;
}
