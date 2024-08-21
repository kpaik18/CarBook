package com.example.demo.admin.car.controller.dto;

import com.example.demo.admin.car.repository.entity.Brand;

public interface CarGetDTO {
    Long getId();

    String getStateNumber();

    String getVINCode();

    Brand getBrand();

    String getModel();
}
