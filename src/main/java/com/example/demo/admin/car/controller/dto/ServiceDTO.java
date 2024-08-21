package com.example.demo.admin.car.controller.dto;

import com.example.demo.admin.car.repository.entity.CarServiceDetail;
import jakarta.validation.constraints.NotNull;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public record ServiceDTO(
        Long id,
        @NotNull String name,
        @NotNull BigDecimal price,
        LocalDateTime createTs
) {
    public static ServiceDTO fromCarServiceDetail(CarServiceDetail detail) {
        return new ServiceDTO(detail.getId(), detail.getName(), detail.getPrice(), detail.getCreateTs());
    }
}
