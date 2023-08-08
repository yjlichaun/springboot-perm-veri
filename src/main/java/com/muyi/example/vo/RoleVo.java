package com.muyi.example.vo;

import com.muyi.example.dto.Menu;
import com.muyi.example.entity.User;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.List;

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
    
    private List<User> users;
    
    private List<Menu> menus;
    
}
