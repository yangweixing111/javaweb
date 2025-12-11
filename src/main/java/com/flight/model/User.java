package com.flight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;

@Data
@TableName("user") // 对应数据库user表
public class User implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO) // 主键自增
    private Long id;             // 用户ID
    private String username;     // 用户名（唯一）
    private String password;     // 密码（加密存储）
    private String realName;     // 真实姓名
    private String idCard;       // 身份证号
    private String phone;        // 手机号
    private Integer role;        // 角色：0-普通用户，1-管理员
}