package com.flight.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.io.Serializable;
import java.sql.Timestamp;

@Data
@TableName("`order`") // order是关键字，需转义
public class Order implements Serializable {
    private static final long serialVersionUID = 1L;

    @TableId(type = IdType.AUTO) // 主键自增
    private Long orderId;         // 订单ID
    private String username;      // 下单用户
    private String flightId;      // 航班ID
    private Integer seatCount;    // 购票数量
    private Double totalPrice;    // 总金额
    private Timestamp createTime; // 下单时间
    private Integer status;       // 订单状态：0-待支付，1-已支付，2-已取消
}