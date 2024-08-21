package com.example.demo.admin.car.repository.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "car")
@Setter
@Getter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car")
    @SequenceGenerator(name = "car", sequenceName = "seq_car", allocationSize = 1, initialValue = 1000)
    private Long id;
    @Column(name = "state_number")
    private String stateNumber;
    @Column(name = "vin_code")
    private String VINCode;
    @Enumerated(EnumType.STRING)
    @Column(name = "brand")
    private Brand brand;
    @Column(name = "model")
    private String model;
    @Builder.Default
    @OneToMany(mappedBy = "car")
    private List<CarServiceDetail> services = new ArrayList<>();
}
