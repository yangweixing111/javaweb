package com.flight.controller;

import com.flight.dto.ResultDTO;
import com.flight.model.Flight;
import com.flight.service.FlightService;
import com.flight.util.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * 航班控制器
 */
@RestController
@RequestMapping("/api/flight")
public class FlightController {

    @Autowired
    private FlightService flightService;

    // 查询所有航班
    @GetMapping
    public ResultDTO<List<Flight>> getAllFlights() {
        return ResultUtil.success(flightService.list());
    }

    // 根据ID查询航班
    @GetMapping("/{id}")
    public ResultDTO<Flight> getFlightById(@PathVariable String id) {
        Flight flight = flightService.getById(id);
        return flight != null ? ResultUtil.success(flight) : (ResultDTO<Flight>) ResultUtil.error("航班不存在");
    }

    // 搜索航班（按出发/到达城市）
    @GetMapping("/search")
    public ResultDTO<List<Flight>> searchFlights(@RequestParam String keyword) {
        List<Flight> flights = flightService.searchFlights(keyword);
        return ResultUtil.success(flights);
    }

    // 新增航班（管理员）
    @PostMapping
    public ResultDTO<?> addFlight(@RequestBody Flight flight) {
        return flightService.addFlight(flight);
    }

    // 更新航班信息
    @PutMapping
    public ResultDTO<?> updateFlight(@RequestBody Flight flight) {
        boolean success = flightService.updateById(flight);
        return success ? ResultUtil.success("航班更新成功") : ResultUtil.error("航班更新失败");
    }

    // 删除航班（管理员）
    @DeleteMapping("/{id}")
    public ResultDTO<?> deleteFlight(@PathVariable String id) {
        return flightService.deleteFlight(id);
    }
}
