package com.example.demo.admin.car.controller.dto;

import com.example.demo.admin.car.repository.entity.Brand;
import com.example.demo.util.CarRegexps;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;

public record CarCreateOrUpdateDTO(
        @NotNull @Pattern(regexp = CarRegexps.stateNumberRegex) String stateNumber,
        @NotNull @Pattern(regexp = CarRegexps.VINCodeRegex) String VINCode,
        @NotNull Brand brand,
        @NotNull String model
) {
}
