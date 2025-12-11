package com.flight.dto;

import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

@Data
public class OrderDTO {
    @NotBlank(message = "航班ID不能为空")
    private String flightId;

    @NotNull(message = "购票数量不能为空")
    private Integer seatCount;

    @NotBlank(message = "用户名不能为空")
    private String username;
}