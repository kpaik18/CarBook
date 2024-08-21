package com.example.demo.admin.car.controller;

import com.example.demo.admin.car.controller.dto.CarCreateOrUpdateDTO;
import com.example.demo.admin.car.controller.dto.CarGetDTO;
import com.example.demo.admin.car.controller.dto.ServiceDTO;
import com.example.demo.admin.car.service.CarService;
import com.example.demo.util.PageView;
import jakarta.annotation.security.RolesAllowed;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import lombok.RequiredArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("admin/cars")
@RestController
@RequiredArgsConstructor
@Validated
public class CarController {
    private final CarService service;

    @GetMapping
    @RolesAllowed("adm_car_read")
    public PageView<CarGetDTO> getCars(@RequestParam @Min(1) int page,
                                       @RequestParam @Min(5) int size,
                                       @RequestParam(required = false) String search) {
        return service.getCars(page, size, search);
    }

    @PostMapping
    @RolesAllowed("adm_car_create")
    public void createCar(@RequestBody @Valid CarCreateOrUpdateDTO dto) {
        service.createCar(dto);
    }

    @PutMapping("{id}")
    @RolesAllowed("adm_car_update")
    public void updateCar(@PathVariable Long id,
                          @RequestBody @Valid CarCreateOrUpdateDTO dto) {
        service.updateCar(id, dto);
    }

    @DeleteMapping("{id}")
    @RolesAllowed("adm_car_delete")
    public void deleteCar(@PathVariable Long id) {
        service.deleteCar(id);
    }

    @PostMapping("{id}/services")
    @RolesAllowed("adm_car_update")
    public void addCarService(@PathVariable Long id,
                              @RequestBody @Valid ServiceDTO serviceDTO) {
        service.addCarService(id, serviceDTO);
    }

    @GetMapping("{id}/services")
    @RolesAllowed("adm_car_read")
    public List<ServiceDTO> getCarServices(@PathVariable Long id) {
        return service.getCarServices(id);
    }
}
