package com.flight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flight.model.Manager;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * 管理员Mapper
 */
@Mapper
public interface ManagerMapper extends BaseMapper<Manager> {
    // 根据用户名查询管理员
    Manager selectByUsername(@Param("username") String username);
}