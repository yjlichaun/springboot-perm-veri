package com.muyi.example.service;

import com.muyi.example.dto.UserInfo;
import com.muyi.example.entity.User;
import com.muyi.example.util.R;
import com.muyi.example.vo.RoleVo;
import com.muyi.example.vo.UserVo;

/**
 * 用户服务接口
 *
 * @author 历川
 * @date 2023-08-07 16:11:48
 */

public interface UserService {
    
    /**
     * 获取用户列表
     * @return R
     */
    R listUsers();
    
    /**
     * 新增用户
     * @param user 用户
     * @return  R
     */
    R addUser(UserVo user);
    
    /**
     * 更新用户信息
     * @param userVo 用户信息
     * @return  R
     */
    R updateUser(UserVo userVo);
    
    /**
     * 通过用户id获取用户信息
     * @param userId 用户id
     * @return R
     */
    R getUserInfoById(int userId);
    
    /**
     * 查询所有角色
     * @return R
     */
    R getAllRoles();
    
    /**
     * 角色列表
     * @return R
     */
    R listRoles();
    
    /**
     * 查询所有权限，分配权限时使用
     * @return
     */
    R listAllPermission();
    
    /**
     * 添加角色
     * @param roleVo 角色
     * @return R
     */
    R addRole(RoleVo roleVo);
    
    /**
     * 修改角色
     * @param roleVo 角色
     * @return R
     */
    R updateRole(RoleVo roleVo);
    
    /**
     * 删除角色
     * @param roleId 角色id
     * @return R
     */
    R deleteRole(int roleId);
}
