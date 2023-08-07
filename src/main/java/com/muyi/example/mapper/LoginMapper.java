package com.muyi.example.mapper;

import com.muyi.example.dto.UserInfo;
import com.muyi.example.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.ResultMap;
import org.apache.ibatis.annotations.Select;

/**
 * @author 历川
 * @version 1.0
 * @description 登录mapper
 * @date 2023/8/7 11:54
 */
@Mapper
public interface LoginMapper {
    
    /**
     * 根据用户名查询用户
     * @param username 用户名
     * @return 用户对象
     */
    @ResultMap("UserMap")
    @Select("select * from sys_user where username = #{username}")
    public User getUserByUsername(@Param("username") String username);
    
    UserInfo getUserInfo(int loginId);
}
