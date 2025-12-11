package com.flight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("flight") // 对应数据库flight表
public class Flight implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.INPUT) // 主键手动输入（航班号）
    private String flightId;        // 航班ID/航班号
    private String departureCity;   // 出发城市
    private String arrivalCity;     // 到达城市
    private Timestamp departureTime; // 出发时间
    private Timestamp arrivalTime;   // 到达时间
    private Integer seats;          // 剩余座位数
    private Double price;           // 票价
}