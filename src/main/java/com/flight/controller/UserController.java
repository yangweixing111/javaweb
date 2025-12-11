package com.flight.controller;

import com.flight.dto.LoginDTO;
import com.flight.dto.ResultDTO;
import com.flight.model.User;
import com.flight.service.UserService;
import com.flight.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    // 用户登录
    @PostMapping("/login")
    public ResultDTO<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        return userService.login(loginDTO);
    }

    // 用户注册
    @PostMapping("/register")
    public ResultDTO<?> register(@RequestBody User user) {
        return userService.register(user);
    }

    // 根据用户名查询用户信息
    @GetMapping("/{username}")
    public ResultDTO<?> getUserInfo(@PathVariable String username) {
        User user = userService.getUserByUsername(username);
        return user != null ? ResultUtil.success(user) : ResultUtil.error("用户不存在");
    }
}