package com.flight.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flight.dto.OrderDTO;
import com.flight.dto.ResultDTO;
import com.flight.mapper.FlightMapper;
import com.flight.mapper.OrderMapper;
import com.flight.model.Flight;
import com.flight.model.Order;
import com.flight.service.OrderService;
import com.flight.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

/**
 * 订单服务实现类
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private OrderMapper orderMapper;

    @Autowired
    private FlightMapper flightMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDTO<?> createOrder(OrderDTO orderDTO) {
        // 1. 查询航班信息
        Flight flight = flightMapper.selectById(orderDTO.getFlightId());
        if (flight == null) {
            return ResultUtil.error("航班不存在");
        }
        // 2. 检查座位是否充足
        if (flight.getSeats() < orderDTO.getSeatCount()) {
            return ResultUtil.error("剩余座位不足");
        }
        // 3. 创建订单
        Order order = new Order();
        order.setUsername(orderDTO.getUsername());
        order.setFlightId(orderDTO.getFlightId());
        order.setSeatCount(orderDTO.getSeatCount());
        order.setTotalPrice(flight.getPrice() * orderDTO.getSeatCount());
        order.setCreateTime(new Timestamp(System.currentTimeMillis()));
        order.setStatus(0); // 待支付
        // 4. 保存订单
        boolean saveSuccess = save(order);
        if (!saveSuccess) {
            return ResultUtil.error("订单创建失败");
        }
        // 5. 更新航班座位数
        int newSeats = flight.getSeats() - orderDTO.getSeatCount();
        flightMapper.updateSeats(flight.getFlightId(), newSeats);
        return ResultUtil.success("订单创建成功");
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public ResultDTO<?> cancelOrder(Long orderId) {
        // 1. 查询订单
        Order order = getById(orderId);
        if (order == null) {
            return ResultUtil.error("订单不存在");
        }
        // 2. 检查订单状态（已支付订单不允许取消）
        if (order.getStatus() == 1) {
            return ResultUtil.error("已支付订单无法取消");
        }
        // 3. 更新订单状态
        order.setStatus(2); // 已取消
        boolean updateSuccess = updateById(order);
        if (!updateSuccess) {
            return ResultUtil.error("订单取消失败");
        }
        // 4. 恢复航班座位数
        Flight flight = flightMapper.selectById(order.getFlightId());
        int newSeats = flight.getSeats() + order.getSeatCount();
        flightMapper.updateSeats(flight.getFlightId(), newSeats);
        return ResultUtil.success("订单取消成功");
    }

    @Override
    public List<Order> getOrdersByUsername(String username) {
        return orderMapper.selectByUsername(username);
    }

    @Override
    public List<Order> getOrdersByFlightId(String flightId) {
        return orderMapper.selectByFlightId(flightId);
    }
}