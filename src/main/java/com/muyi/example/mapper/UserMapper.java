package com.muyi.example.mapper;

import com.muyi.example.dto.UserInfo;
import com.muyi.example.entity.Role;
import com.muyi.example.entity.User;
import com.muyi.example.util.R;
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
    
    //TODO:2023-08-07 22:11:45
    List<Role> listRoles();
}
