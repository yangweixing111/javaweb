package com.flight.controller;

import com.flight.dto.OrderDTO;
import com.flight.dto.ResultDTO;
import com.flight.model.Order;
import com.flight.service.OrderService;
import com.flight.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * 订单控制器
 */
@RestController
@RequestMapping("/api/order")
public class OrderController {

    @Autowired
    private OrderService orderService;

    // 创建订单（购票）
    @PostMapping
    public ResultDTO<?> createOrder(@Valid @RequestBody OrderDTO orderDTO) {
        return orderService.createOrder(orderDTO);
    }

    // 取消订单
    @PutMapping("/cancel/{id}")
    public ResultDTO<?> cancelOrder(@PathVariable Long id) {
        return orderService.cancelOrder(id);
    }

    // 根据用户名查询订单
    @GetMapping("/user/{username}")
    public ResultDTO<List<Order>> getOrdersByUsername(@PathVariable String username) {
        List<Order> orders = orderService.getOrdersByUsername(username);
        return ResultUtil.success(orders);
    }

    // 根据航班ID查询订单
    @GetMapping("/flight/{flightId}")
    public ResultDTO<List<Order>> getOrdersByFlightId(@PathVariable String flightId) {
        List<Order> orders = orderService.getOrdersByFlightId(flightId);
        return ResultUtil.success(orders);
    }
}