package com.flight.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flight.dto.OrderDTO;
import com.flight.model.Order;
import com.flight.dto.ResultDTO;

import java.util.List;

/**
 * 订单服务接口
 */
public interface OrderService extends IService<Order> {
    // 创建订单（购票）
    ResultDTO<?> createOrder(OrderDTO orderDTO);

    // 取消订单
    ResultDTO<?> cancelOrder(Long orderId);

    // 根据用户名查询订单
    List<Order> getOrdersByUsername(String username);

    // 根据航班ID查询订单
    List<Order> getOrdersByFlightId(String flightId);
}
