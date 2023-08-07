package com.muyi.example.service.impl;

import cn.dev33.satoken.stp.StpInterface;
import cn.dev33.satoken.stp.StpUtil;
import com.muyi.example.dto.UserInfo;
import com.muyi.example.mapper.LoginMapper;
import com.muyi.example.service.LoginService;
import com.muyi.example.util.R;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @author 历川
 * @version 1.0
 * @description 自定义权限加载接口实现类
 * @date 2023/8/7 16:04
 */

@Component
public class StpInterfaceImpl implements StpInterface {
    
    @Autowired
    LoginMapper loginMapper;
    /**
     * 返回一个账号所拥有的权限码集合
     * @param loginId 账号id
     * @param loginType 账号类型
     * @return 权限集合
     */
    @Override
    public List<String> getPermissionList(Object loginId, String loginType) {
        int id = StpUtil.getLoginIdAsInt();
        UserInfo info = loginMapper.getUserInfo(id);
        Set<String> permissionList = info.getPermissionList();
        List<String> list = new ArrayList<String>(permissionList);
        return list;
    }
    
    /**
     * 获取一个账号所拥有的角色标识集合(权限与角色可分开校验)
     * @param loginId 账号id
     * @param loginType 账号类型
     * @return 角色标识集合
     */
    @Override
    public List<String> getRoleList(Object loginId, String loginType) {
        int id = StpUtil.getLoginIdAsInt();
        UserInfo info = loginMapper.getUserInfo(id);
        Set<String> menuList = info.getMenuList();
        List<String> list = new ArrayList<>(menuList);
        return list;
    }
}
