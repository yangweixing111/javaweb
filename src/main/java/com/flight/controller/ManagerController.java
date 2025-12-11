package com.flight.controller;

import com.flight.dto.LoginDTO;
import com.flight.dto.ResultDTO;
import com.flight.model.Manager;
import com.flight.service.ManagerService;
import com.flight.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 管理员控制器
 */
@RestController
@RequestMapping("/api/manager")
public class ManagerController {

    @Autowired
    private ManagerService managerService;

    // 管理员登录
    @PostMapping("/login")
    public ResultDTO<?> login(@Valid @RequestBody LoginDTO loginDTO) {
        return managerService.login(loginDTO);
    }

    // 根据用户名查询管理员信息
    @GetMapping("/{username}")
    public ResultDTO<?> getManagerInfo(@PathVariable String username) {
        Manager manager = managerService.getManagerByUsername(username);
        return manager != null ? ResultUtil.success(manager) : ResultUtil.error("管理员不存在");
    }
}