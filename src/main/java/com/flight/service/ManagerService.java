package com.flight.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flight.dto.LoginDTO;
import com.flight.model.Manager;
import com.flight.dto.ResultDTO;

/**
 * 管理员服务接口
 */
public interface ManagerService extends IService<Manager> {
    // 管理员登录
    ResultDTO<?> login(LoginDTO loginDTO);

    // 根据用户名查询管理员
    Manager getManagerByUsername(String username);
}