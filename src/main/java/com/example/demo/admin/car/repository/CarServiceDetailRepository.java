package com.example.demo.admin.car.repository;

import com.example.demo.admin.car.repository.entity.CarServiceDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CarServiceDetailRepository extends JpaRepository<CarServiceDetail, Long> {

    @Query("""
                    select csd 
                    from CarServiceDetail csd
                    where csd.car.id = :carId
                    order by csd.createTs desc
            """)
    List<CarServiceDetail> getCarServices(Long carId);
}
