package com.flight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flight.model.Order;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 订单Mapper
 */
@Mapper
public interface OrderMapper extends BaseMapper<Order> {
    // 根据用户名查询订单
    List<Order> selectByUsername(@Param("username") String username);

    // 根据航班ID查询订单
    List<Order> selectByFlightId(@Param("flightId") String flightId);
}