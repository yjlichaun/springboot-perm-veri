package com.muyi.example.service.impl;

import cn.dev33.satoken.stp.StpUtil;
import com.muyi.example.dto.LoginUser;
import com.muyi.example.dto.UserInfo;
import com.muyi.example.entity.User;
import com.muyi.example.mapper.LoginMapper;
import com.muyi.example.service.LoginService;
import com.muyi.example.util.R;
import com.muyi.example.util.constants.Constants;
import com.muyi.example.util.constants.ErrorEnum;
import lombok.extern.slf4j.Slf4j;
import org.checkerframework.checker.units.qual.A;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 历川
 * @version 1.0
 * @description 登录接口实现类
 * @date 2023/8/7 13:01
 */

@Slf4j
@Service
public class LoginServiceImpl implements LoginService {
    
    @Autowired
    LoginMapper loginMapper;
    
    @Override
    public R login(LoginUser loginUser) {
        String username = loginUser.getUsername();
        User user = loginMapper.getUserByUsername(username);
        if (user == null) {
            return R.failed(null,ErrorEnum.E_10010.getErrorCode(),ErrorEnum.E_10010.getErrorMsg());
        }
        if (!user.getPassword().equals(loginUser.getPassword())) {
            return R.failed(null,ErrorEnum.E_10010.getErrorCode(),ErrorEnum.E_10010.getErrorMsg());
        }
        StpUtil.login(user.getUserId());
        return R.ok(user,"登录成功");
    }
    
    @Override
    public R getInfo() {
        int loginId = StpUtil.getLoginIdAsInt();
        UserInfo userinfo = loginMapper.getUserInfo(loginId);
        return R.ok(userinfo);
    }
    
    @Override
    public R logout() {
        StpUtil.logout();
        return R.ok("登出成功");
    }
}
