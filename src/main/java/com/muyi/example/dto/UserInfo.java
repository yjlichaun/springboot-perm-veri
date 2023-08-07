package com.muyi.example.dto;

import lombok.Data;

import java.util.List;
import java.util.Set;

/**
 * @author 历川
 * @version 1.0
 * @description 用户信息类
 * @date 2023/8/7 11:55
 */
@Data
public class UserInfo {
    private int userId;
    private String username;
    private String nickname;
    private List<Integer> roleIds;
    private Set<String> menuList;
    private Set<String> permissionList;
}
