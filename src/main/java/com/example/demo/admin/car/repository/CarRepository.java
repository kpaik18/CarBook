package com.example.demo.admin.car.repository;

import com.example.demo.admin.car.controller.dto.CarGetDTO;
import com.example.demo.admin.car.repository.entity.Car;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepository extends JpaRepository<Car, Long> {
    boolean existsByVINCodeOrStateNumber(String vinCode, String stateNumber);

    @Query("""
            select c 
            from Car c
            where :search is null or (
                c.stateNumber ilike %:search% or c.VINCode ilike %:search% or 
                c.model ilike %:search%
            )
            """)
    Page<CarGetDTO> getCars(String search, Pageable pageable);
}
