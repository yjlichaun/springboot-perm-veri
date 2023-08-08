package com.muyi.example.controller;

import cn.dev33.satoken.annotation.SaCheckPermission;
import cn.dev33.satoken.annotation.SaMode;
import com.muyi.example.dto.UserInfo;
import com.muyi.example.entity.Role;
import com.muyi.example.entity.User;
import com.muyi.example.service.UserService;
import com.muyi.example.util.CommonUtil;
import com.muyi.example.util.R;
import com.muyi.example.vo.RoleVo;
import com.muyi.example.vo.UserVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * @author 历川
 * @version 1.0
 * @description 用户控制层
 * @date 2023/8/7 15:58
 */
@RestController
@RequestMapping("/user")
public class UserController {
    
    
    @Autowired
    UserService userService;
    /**
     * 查询用户列表
     * @return R
     */
    @SaCheckPermission("user:list")
    @GetMapping("/list")
    public R listUsers() {
        return userService.listUsers();
    }
    
    /**
     * 新增用户
     * @param user 用户
     * @return R
     */
    @SaCheckPermission("user:add")
    @PostMapping("/addUser")
    public R addUser(@RequestBody UserVo user) {
        return userService.addUser(user);
    }
    
    /**
     * 更新前获取用户信息
     * @param userId 用户id
     * @return R
     */
    @SaCheckPermission("user:update")
    @GetMapping("/updateUser/{userId}")
    public R getUserInfo(@PathVariable int userId){
        return userService.getUserInfoById(userId);
    }
    
    
    /**
     * 更新用户信息
     * @param userVo 用户信息
     * @return R
     */
    @SaCheckPermission("user:update")
    @PutMapping("/updateUser")
    public R  updateUser(@RequestBody UserVo userVo){
        return userService.updateUser(userVo);
    }
    
    /**
     * 获取全部角色信息
     * @return R
     */
    @SaCheckPermission(value = {"user:add","user:update"},mode = SaMode.OR)
    @GetMapping("/getAllRoles")
    public R getAllRoles() {
        return userService.getAllRoles();
    }
    
    /**
     * 角色列表
     * @return R
     */
    @SaCheckPermission("role:list")
    @GetMapping("/listRoles")
    public R listRoles() {
        return userService.listRoles();
    }
    
    
    /**
     * 查询所有权限，分配权限时调用
     * @return R
     */
    @SaCheckPermission("role:list")
    @GetMapping("/listAllPermission")
    public R listAllPermissions() {
        return userService.listAllPermission();
    }
    
    /**
     * 新增角色
     * @param roleVo 角色
     * @return R
     */
    @SaCheckPermission("role:add")
    @PostMapping("addRole")
    public R addRole(@RequestBody RoleVo roleVo) {
        return userService.addRole(roleVo);
    }
    
    /**
     * 修改角色
     * @param roleVo 角色
     * @return R
     */
    @SaCheckPermission("role:update")
    @PutMapping("/updateRole")
    public R updateRole(@RequestBody RoleVo roleVo) {
        return userService.updateRole(roleVo);
    }
    
    /**
     * 删除角色
     * @param roleId 角色Id
     * @return R
     */
    @SaCheckPermission("role:delete")
    @DeleteMapping("/deleteRole/{roleId}")
    public R deleteRole(@PathVariable int roleId) {
        return userService.deleteRole(roleId);
    }
}
