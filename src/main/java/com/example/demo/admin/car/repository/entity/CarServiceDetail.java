package com.example.demo.admin.car.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(name = "car_service_detail")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class CarServiceDetail {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_service_detail")
    @SequenceGenerator(name = "car_service_detail", sequenceName = "seq_car_service_detail", allocationSize = 1, initialValue = 1000)
    private Long id;
    @Column(name = "name")
    private String name;
    @Column(name = "create_ts")
    private LocalDateTime createTs;
    @Column(name = "price")
    private BigDecimal price;
    @ManyToOne
    private Car car;
}
