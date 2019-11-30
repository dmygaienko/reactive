package com.mygaienko.reactive.api.dto;

import com.mygaienko.reactive.repo.entity.Car;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class CarDto {

    String id;

    String brand;

    String model;

    public Car toEntity() {
        return Car.builder()
                .id(id)
                .brand(brand)
                .model(model)
                .build();
    }

    public static CarDto toDto(Car car) {
        return new CarDto(car.getId(), car.getBrand(), car.getModel());
    }

}
