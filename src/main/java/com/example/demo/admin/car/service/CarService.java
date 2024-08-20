package com.example.demo.admin.car.service;

import com.example.demo.admin.car.controller.dto.CarCreateOrUpdateDTO;
import com.example.demo.admin.car.repository.CarRepository;
import com.example.demo.admin.car.repository.entity.Car;
import com.example.demo.exception.SecurityViolationException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;

    public void createCar(CarCreateOrUpdateDTO dto) {
        Car car = new Car();
        updateEntityFromDto(car, dto);
        repository.save(car);
    }

    public void updateCar(Long id, CarCreateOrUpdateDTO dto) {
        Car dbCar = lookupCar(id);
        updateEntityFromDto(dbCar, dto);
    }

    private void updateEntityFromDto(Car dbCar, CarCreateOrUpdateDTO dto) {
        dbCar.setStateNumber(dto.stateNumber());
        dbCar.setVINCode(dto.VINCode());
        dbCar.setBrand(dto.brand());
        dbCar.setModel(dto.model());
    }

    public Car lookupCar(Long id) {
        return repository.findById(id).orElseThrow(SecurityViolationException::new);
    }
}
