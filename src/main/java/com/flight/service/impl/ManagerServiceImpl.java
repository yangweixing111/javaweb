package com.flight.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flight.dto.LoginDTO;
import com.flight.dto.ResultDTO;
import com.flight.mapper.ManagerMapper;
import com.flight.model.Manager;
import com.flight.service.ManagerService;
import com.flight.util.JwtUtil;
import com.flight.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 管理员服务实现类
 */
@Service
public class ManagerServiceImpl extends ServiceImpl<ManagerMapper, Manager> implements ManagerService {

    @Autowired
    private ManagerMapper managerMapper;

    @Autowired
    private JwtUtil jwtUtil;

    // 密码加密器
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResultDTO<?> login(LoginDTO loginDTO) {
        // 查询管理员
        Manager manager = managerMapper.selectByUsername(loginDTO.getUsername());
        if (manager == null) {
            return ResultUtil.error("管理员账号不存在");
        }
        // 验证密码
        if (!passwordEncoder.matches(loginDTO.getPassword(), manager.getPassword())) {
            return ResultUtil.error("密码错误");
        }
        // 生成token
        String token = jwtUtil.generateToken(manager.getUsername());
        return ResultUtil.success(token);
    }

    @Override
    public Manager getManagerByUsername(String username) {
        return managerMapper.selectByUsername(username);
    }
}