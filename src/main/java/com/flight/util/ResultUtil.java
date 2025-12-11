package com.flight.util;

import com.flight.dto.ResultDTO;

/**
 * 统一响应结果工具类
 */
public class ResultUtil {
    // 成功响应（带数据）
    public static <T> ResultDTO<T> success(T data) {
        return new ResultDTO<>(200, "操作成功", data);
    }

    // 成功响应（不带数据）
    public static ResultDTO<?> success(String message) {
        return new ResultDTO<>(200, message, null);
    }

    // 失败响应
    public static ResultDTO<?> error(String message) {
        return new ResultDTO<>(500, message, null);
    }

    // 未登录响应
    public static ResultDTO<?> unAuthorized() {
        return new ResultDTO<>(401, "请先登录", null);
    }

    // 无权限响应
    public static ResultDTO<?> forbidden() {
        return new ResultDTO<>(403, "无操作权限", null);
    }
}