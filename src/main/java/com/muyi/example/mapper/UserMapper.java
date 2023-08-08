package com.muyi.example.mapper;

import com.muyi.example.dto.Permission;
import com.muyi.example.dto.UserInfo;
import com.muyi.example.entity.Role;
import com.muyi.example.entity.User;
import com.muyi.example.util.R;
import com.muyi.example.vo.RoleVo;
import com.muyi.example.vo.UserVo;
import org.apache.ibatis.annotations.*;
import org.mybatis.spring.annotation.MapperScan;

import java.util.List;

/**
 * @author 历川
 * @version 1.0
 * @description 用户mapper
 * @date 2023/8/7 10:32
 */

@Mapper
public interface UserMapper {
    
    /**
     * 获取全部用户列表
     * @return 用户列表
     */
    @ResultMap("UserMap")
    @Select("select * from sys_user")
    List<User> getUserList();
    
    
    /**
     * 添加用户
     * @param user 用户
     * @return R
     */
    
    @ResultMap("UserMap")
    @Insert("insert into sys_user (username, password, nickname, create_time, update_time, delete_status) " +
            "values (#{username},#{password},#{nickname},#{createTime},#{updateTime},#{deleteStatus}) ")
    int insertUser(User user);
    
    /**
     * 绑定用户角色
     * @param user 用户vo
     * @return int
     */
    int batchAndUserRole(UserVo user);
    
    /**
     * 通过用户id获取用户信息
     * @param userId
     * @return
     */
    UserVo getUserInfo(int userId);
    
    /**
     * 更新用户信息
     * @param user 用户信息
     * @return 更新的行数
     */
    @Update("update sys_user set nickname = #{nickname},password = #{password}," +
            "delete_status = #{deleteStatus} where id = #{userId} and id != 10001")
    int updateUser(User user);
    
    /**
     * 一出用户角色信息
     * @param userId 用户id
     * @return 影响行数
     */
    @Delete("delete from sys_user_role where user_id = #{userId}")
    int removeUserAllRoleByUserId(@Param("userId") Integer userId);
    
    /**
     * 查询全部角色
     * @return 全部角色列表
     */
    @Select("select id roleId , role_name roleName from sys_role where delete_status = '1'")
    List<Role> getAllRoles();
    
    /**
     * 获取角色列表
     * @return 角色列表
     */
    List<Role> listRoles();
    
    /**
     * 获取全部权限
     * @return 获取权限
     */
    List<Permission> listAllPermissions();
    
    /**
     * 添加角色
     * @param role 角色
     * @return 影响行数
     */
    @Insert("insert into sys_role (role_name, create_time, update_time, delete_status) " +
            "values (#{roleName},#{createTime},#{updateTime},#{deleteStatus})")
    int addRole(Role role);
    
    /**
     * 通过角色名称获取角色
     * @param roleName 角色名称
     * @return 角色信息
     */
    @Select("select * from sys_role where role_name = #{roleName}")
    Role getRoleByRoleName(@Param("roleName") String roleName);
    
    /**
     * 绑定角色权限
     * @param id 角色id
     * @param permissions 权限
     * @param dateNow 绑定日期
     * @return 影响行数
     */
    int batchAndRolePerm(@Param("id") Integer id,
                         @Param("permissions") List<Permission> permissions,
                         @Param("dateNow") String dateNow);
    
    /**
     * 更新角色信息
     * @param roleName 角色名称
     * @param roleId 角色id
     * @param dateNow 更新日期
     * @return 影响行数
     */
    @Update("update sys_role set " +
            "role_name = #{roleName}," +
            "update_time = #{dateNow} " +
            "where id = #{roleId}")
    int updateRole(@Param("roleName") String roleName,
                   @Param("roleId") int roleId,
                   @Param("dateNow") String dateNow);
    
    /**
     * 移除角色权限关系
     * @param roleId 角色id
     * @return 影响行数
     */
    @Update("update sys_role_permission set delete_status = '2' where role_id = #{roleId}")
    int removeRolePerm(@Param("roleId") Integer roleId);
    
    /**
     * 通过角色id获取角色信息
     * @param roleId 角色id
     * @return Role
     */
    @Select("select * from sys_role where id = #{roleId}")
    Role getRoleByRoleId(@Param("roleId") int roleId);
    
    /**
     * 删除角色
     * @param roleId 角色id
     * @return 影响行数
     */
    @Update("update sys_role set delete_status = '2' where id = #{roleId}")
    int deleteRole(int roleId);
}
