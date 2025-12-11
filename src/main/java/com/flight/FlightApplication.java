package com.flight;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.flight.mapper") // 扫描Mapper接口
public class FlightApplication {
    public static void main(String[] args) {
        SpringApplication.run(FlightApplication.class, args);
    }
}