package com.flight.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.flight.dto.ResultDTO;
import com.flight.mapper.FlightMapper;
import com.flight.model.Flight;
import com.flight.service.FlightService;
import com.flight.util.ResultUtil;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * 航班服务实现类
 */
@Service
public class FlightServiceImpl extends ServiceImpl<FlightMapper, Flight> implements FlightService {

    @Override
    public List<Flight> searchFlights(String keyword) {
        return baseMapper.searchByCity(keyword);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public boolean updateSeats(String flightId, int newSeats) {
        return baseMapper.updateSeats(flightId, newSeats) > 0;
    }

    @Override
    public ResultDTO<?> addFlight(Flight flight) {
        // 检查航班号是否已存在
        if (getById(flight.getFlightId()) != null) {
            return ResultUtil.error("航班号已存在");
        }
        boolean success = save(flight);
        return success ? ResultUtil.success("航班添加成功") : ResultUtil.error("航班添加失败");
    }

    @Override
    public ResultDTO<?> deleteFlight(String flightId) {
        boolean success = removeById(flightId);
        return success ? ResultUtil.success("航班删除成功") : ResultUtil.error("航班删除失败");
    }
}