package com.muyi.example.vo;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

/**
 * @author 历川
 * @version 1.0
 * @description 角色Vo类
 * @date 2023/8/7 22:01
 */
@Data
@NoArgsConstructor
public class RoleVo {
    
    private Integer roleId;
    
    private String roleName;
    
    private Integer userId;
    
    private String nickname;
    
    private Integer permissionId;
    
    private String menuCode;
    
    private String menuName;
    
    private String permissionName;
}
