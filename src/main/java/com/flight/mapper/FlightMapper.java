package com.flight.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.flight.model.Flight;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * 航班Mapper
 */
@Mapper
public interface FlightMapper extends BaseMapper<Flight> {
    // 根据出发/到达城市搜索航班
    List<Flight> searchByCity(@Param("keyword") String keyword);

    // 更新航班座位数
    int updateSeats(@Param("flightId") String flightId, @Param("newSeats") Integer newSeats);
}
