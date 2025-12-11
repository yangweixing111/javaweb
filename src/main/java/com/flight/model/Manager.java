package com.flight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("manager") // 对应数据库manager表
public class Manager implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO)
    private Long id;         // 管理员ID
    private String username; // 管理员账号
    private String password; // 管理员密码
    private String name;     // 管理员姓名
}