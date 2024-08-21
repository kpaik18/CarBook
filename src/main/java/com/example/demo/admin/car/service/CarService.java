package com.example.demo.admin.car.service;

import com.example.demo.admin.car.controller.dto.CarCreateOrUpdateDTO;
import com.example.demo.admin.car.controller.dto.CarGetDTO;
import com.example.demo.admin.car.controller.dto.ServiceDTO;
import com.example.demo.admin.car.repository.CarRepository;
import com.example.demo.admin.car.repository.CarServiceDetailRepository;
import com.example.demo.admin.car.repository.entity.Car;
import com.example.demo.admin.car.repository.entity.CarServiceDetail;
import com.example.demo.exception.BusinessException;
import com.example.demo.exception.SecurityViolationException;
import com.example.demo.util.PageUtils;
import com.example.demo.util.PageView;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class CarService {
    private final CarRepository repository;
    private final CarServiceDetailRepository serviceDetailRepository;
    private final CarServiceDetailRepository carServiceDetailRepository;

    public void createCar(CarCreateOrUpdateDTO dto) {
        Car car = new Car();
        updateEntityFromDto(car, dto);
        saveCar(car);
    }

    public void updateCar(Long id, CarCreateOrUpdateDTO dto) {
        Car dbCar = lookupCar(id);
        updateEntityFromDto(dbCar, dto);
        saveCar(dbCar);
    }

    private void saveCar(Car dbCar) {
        try {
            repository.saveAndFlush(dbCar);
        } catch (DataIntegrityViolationException ex) {
            throw new BusinessException("car_with_same_data_exists");
        }
    }


    private void updateEntityFromDto(Car dbCar, CarCreateOrUpdateDTO dto) {
        dbCar.setStateNumber(dto.stateNumber());
        dbCar.setVINCode(dto.VINCode());
        dbCar.setBrand(dto.brand());
        dbCar.setModel(dto.model());
    }

    public void deleteCar(Long id) {
        Car car = lookupCar(id);
        repository.delete(car);
    }

    public Car lookupCar(Long id) {
        return repository.findById(id).orElseThrow(SecurityViolationException::new);
    }

    public PageView<CarGetDTO> getCars(int page, int size, String search) {
        return PageView.of(repository.getCars(search, PageUtils.pageOf(page, size)));
    }

    public void addCarService(Long id, ServiceDTO serviceDTO) {
        Car car = lookupCar(id);
        CarServiceDetail serviceDetail = CarServiceDetail
                .builder()
                .name(serviceDTO.name())
                .price(serviceDTO.price())
                .createTs(LocalDateTime.now())
                .build();
        car.getServices().add(serviceDetail);
        serviceDetail.setCar(car);
        serviceDetailRepository.save(serviceDetail);
    }

    public List<ServiceDTO> getCarServices(Long id) {
        List<CarServiceDetail> serviceDetails = carServiceDetailRepository.getCarServices(id);
        return serviceDetails
                .stream()
                .map(ServiceDTO::fromCarServiceDetail)
                .toList();
    }
}
