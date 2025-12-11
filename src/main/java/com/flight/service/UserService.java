package com.flight.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flight.dto.LoginDTO;
import com.flight.model.User;
import com.flight.dto.ResultDTO;

/**
 * 用户服务接口
 */
public interface UserService extends IService<User> {
    // 用户登录
    ResultDTO<?> login(LoginDTO loginDTO);

    // 用户注册
    ResultDTO<?> register(User user);

    // 根据用户名查询用户
    User getUserByUsername(String username);
}