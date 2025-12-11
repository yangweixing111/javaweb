package com.flight.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.flight.model.Flight;
import com.flight.dto.ResultDTO;

import java.util.List;

/**
 * 航班服务接口
 */
public interface FlightService extends IService<Flight> {
    // 搜索航班（按出发/到达城市）
    List<Flight> searchFlights(String keyword);

    // 更新航班座位数
    boolean updateSeats(String flightId, int newSeats);

    // 新增航班
    ResultDTO<?> addFlight(Flight flight);

    // 删除航班
    ResultDTO<?> deleteFlight(String flightId);
}
