package com.example.demo.admin.car.controller;

import com.example.demo.admin.car.controller.dto.CarCreateOrUpdateDTO;
import com.example.demo.admin.car.service.CarService;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("admin/cars")
@RestController
@RequiredArgsConstructor
@Validated
public class CarController {
    private final CarService service;

    @PostMapping
    @RolesAllowed("adm_car_create")
    public void createCar(@RequestBody @Valid CarCreateOrUpdateDTO dto) {
        service.createCar(dto);
    }

    @PutMapping("{id}")
    public void updateCar(@PathVariable Long id,
                          @RequestBody @Valid CarCreateOrUpdateDTO dto) {
        service.updateCar(id, dto);
    }
}
