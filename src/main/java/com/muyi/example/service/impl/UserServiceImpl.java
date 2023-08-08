package com.muyi.example.service.impl;

import com.muyi.example.dto.Menu;
import com.muyi.example.dto.Permission;
import com.muyi.example.dto.UserInfo;
import com.muyi.example.entity.Role;
import com.muyi.example.entity.User;
import com.muyi.example.mapper.LoginMapper;
import com.muyi.example.mapper.UserMapper;
import com.muyi.example.service.UserService;
import com.muyi.example.util.CommonUtil;
import com.muyi.example.util.DateUtil;
import com.muyi.example.util.R;
import com.muyi.example.util.constants.ErrorEnum;
import com.muyi.example.vo.RoleVo;
import com.muyi.example.vo.UserVo;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

/**
 * @author 历川
 * @version 1.0
 * @description 用户接口实现类
 * @date 2023/8/7 16:12
 */
@Slf4j
@Service
public class UserServiceImpl implements UserService {
    
    @Autowired
    UserMapper userMapper;
    
    @Autowired
    LoginMapper loginMapper;
    
    @Override
    public R listUsers() {
        List<User> list = userMapper.getUserList();
        if (list == null) {
            return R.ok("当前还没有用户");
        }
        return R.ok(list);
    }
    
    @Override
    @Transactional
    public R addUser(UserVo user) {
        if ("".equals(user.getUsername()) || user.getUsername() == null) {
            return R.failed("用户名不能为空");
        }
        if ("".equals(user.getPassword()) || user.getPassword() == null) {
            return R.failed("用户密码不能为空");
        }
        if ("".equals(user.getNickname()) || user.getNickname() == null) {
            return R.failed("用户昵称不能为空");
        }
        User userTmp = loginMapper.getUserByUsername(user.getUsername());
        if (userTmp != null) {
            R.failed(null, ErrorEnum.E_10009.getErrorCode(), ErrorEnum.E_10009.getErrorMsg());
        }
        user.setCreateTime(DateUtil.getDateNow());
        user.setUpdateTime(DateUtil.getDateNow());
        user.setDeleteStatus("1");
        int cnt1 = userMapper.insertUser(user);
        if (cnt1 == 0) {
            return R.failed("添加用户失败");
        }
        User newUser = loginMapper.getUserByUsername(user.getUsername());
        user.setUserId(newUser.getUserId());
        int cnt2 = userMapper.batchAndUserRole(user);
        if (cnt2 == 0) {
            return R.failed("添加用户失败");
        }
        return R.ok("添加用户成功");
    }
    
    @Override
    @Transactional
    public R updateUser(UserVo userVo) {
        if (userVo.getUserId() == 10001) {
            return R.failed("无法修改管理员信息");
        }
        if (userVo.getUsername() == null || "".equals(userVo.getUsername())) {
            return R.failed("用户名称不能为空");
        }
        if (userVo.getNickname() == null || "".equals(userVo.getNickname())) {
            return R.failed("用户昵称不能为空");
        }
        User user = new User();
        BeanUtils.copyProperties(userVo, user);
        user.setUpdateTime(DateUtil.getDateNow());
        int updateCnt = userMapper.updateUser(user);
        if (updateCnt == 0) {
            R.failed("更新失败");
        }
        
        int removeCnt = userMapper.removeUserAllRoleByUserId(user.getUserId());
        
        if (removeCnt == 0) {
            R.failed("移除用户角色失败");
        }
        
        if (userVo.getRoles() != null) {
            userMapper.batchAndUserRole(userVo);
        }
        return R.ok("用户更新完成");
    }
    
    @Override
    public R getUserInfoById(int userId) {
        UserVo userInfo = userMapper.getUserInfo(userId);
        if (userInfo == null) {
            return R.failed("该用户不存在");
        }
        return R.ok(userInfo);
    }
    
    @Override
    public R getAllRoles() {
        List<Role> roles = userMapper.getAllRoles();
        if (roles == null) {
            return R.ok("目前暂无角色");
        }
        return R.ok(roles, "查询成功！！！");
    }
    
    @Override
    public R listRoles() {
        List<Role> list = userMapper.listRoles();
        if (list == null) {
            return R.failed("未获取到数据");
        }
        return R.ok(list);
    }
    
    @Override
    public R listAllPermission() {
        List<Permission> permissions = userMapper.listAllPermissions();
        if (permissions == null) {
            return R.failed("未查询到权限列表");
        }
        return R.ok(permissions);
    }
    
    @Override
    @Transactional
    public R addRole(RoleVo roleVo) {
        String roleName = roleVo.getRoleName();
        List<Permission> permissions = getPermission(roleVo);
        if (roleName == null || ("").equals(roleName)) {
            return R.failed("角色名称不能为空");
        }
        
        Role tmpRole = userMapper.getRoleByRoleName(roleName);
        if (tmpRole != null) {
            return R.failed("角色已经存在,不能添加");
        }
        Role role = new Role();
        role.setRoleName(roleName);
        role.setCreateTime(DateUtil.getDateNow());
        role.setUpdateTime(DateUtil.getDateNow());
        role.setDeleteStatus("1");
        int addCnt = userMapper.addRole(role);
        if (addCnt == 0) {
            return R.failed("添加失败");
        }
        role = userMapper.getRoleByRoleName(roleName);
        if (permissions.size() > 0) {
            int batchCnt = userMapper.batchAndRolePerm(role.getId(),permissions,DateUtil.getDateNow());
            if (batchCnt == 0) {
                return R.failed("绑定角色和权限时错误");
            }
        }
        return R.ok(role,"添加角色成功");
    }
    
    @Override
    @Transactional
    public R updateRole(RoleVo roleVo) {
        String roleName = roleVo.getRoleName();
        List<Permission> permissions = getPermission(roleVo);
        if (roleName == null || ("").equals(roleName)) {
            return R.failed("角色名不能为空");
        }
        Role roleTmp = userMapper.getRoleByRoleName(roleName);
        if (roleTmp != null) {
            return R.failed("该角色已经存在，无法修改");
        }
        int updateCnt = userMapper.updateRole(roleVo.getRoleName(),roleVo.getRoleId(),DateUtil.getDateNow());
        if (updateCnt == 0) {
            return R.failed("更新失败!!!");
        }
        int removeCnt = userMapper.removeRolePerm(roleVo.getRoleId());
        if (removeCnt == 0) {
            return R.failed("移除角色权限关系失败");
        }
        if (permissions.size() > 0) {
            int batchCnt = userMapper.batchAndRolePerm(roleVo.getRoleId(),permissions,DateUtil.getDateNow());
            if (batchCnt == 0) {
                return R.failed("绑定角色和权限时错误");
            }
        }
        return R.ok("修改角色成功");
    }
    
    @Override
    @Transactional
    public R deleteRole(int roleId) {
        Role role = userMapper.getRoleByRoleId(roleId);
        if  (role == null) {
            return R.failed("角色不存在,无法删除");
        }
        int deleteCnt = userMapper.deleteRole(roleId);
        if (deleteCnt == 0) {
            return R.failed("删除失败");
        }
        int removeCnt = userMapper.removeRolePerm(roleId);
        return R.ok("删除成功");
    }
    
    public List<Permission> getPermission(RoleVo roleVo) {
        List<Menu> menus = roleVo.getMenus();
        List<Permission> permissions = new ArrayList<>();
        if (menus != null) {
            for (Menu menu : menus) {
                if (menu == null) {
                    continue;
                }
                List<Permission> tmp = menu.getPermissions();
                if (tmp != null) {
                    permissions.addAll(permissions.size(),tmp);
                }
            }
        }
        return permissions;
    }
}
