package com.flight.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flight.dto.LoginDTO;
import com.flight.dto.ResultDTO;
import com.flight.mapper.UserMapper;
import com.flight.model.User;
import com.flight.service.UserService;
import com.flight.util.JwtUtil;
import com.flight.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * 用户服务实现类
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

    @Autowired
    private UserMapper userMapper;

    @Autowired
    private JwtUtil jwtUtil;

    // 密码加密器
    private final BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Override
    public ResultDTO<?> login(LoginDTO loginDTO) {
        // 查询用户
        User user = userMapper.selectByUsername(loginDTO.getUsername());
        if (user == null) {
            return ResultUtil.error("用户名不存在");
        }
        // 验证密码
        if (!passwordEncoder.matches(loginDTO.getPassword(), user.getPassword())) {
            return ResultUtil.error("密码错误");
        }
        // 生成token
        String token = jwtUtil.generateToken(user.getUsername());
        return ResultUtil.success(token);
    }

    @Override
    public ResultDTO<?> register(User user) {
        // 检查用户名是否已存在
        if (userMapper.selectByUsername(user.getUsername()) != null) {
            return ResultUtil.error("用户名已存在");
        }
        // 密码加密
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        // 默认角色：普通用户
        user.setRole(0);
        // 保存用户
        boolean success = save(user);
        return success ? ResultUtil.success("注册成功") : ResultUtil.error("注册失败");
    }

    @Override
    public User getUserByUsername(String username) {
        return userMapper.selectByUsername(username);
    }
}