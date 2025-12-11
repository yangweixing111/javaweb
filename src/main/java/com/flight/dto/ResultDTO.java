package com.flight.dto;

import lombok.Data;

import java.io.Serializable;

@Data
public class ResultDTO<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private int code;       // 状态码：200成功，500失败，401未登录，403无权限
    private String message; // 提示信息
    private T data;         // 响应数据

    // 构造方法
    public ResultDTO(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }
}