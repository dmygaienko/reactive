package com.mygaienko.reactive.service;

import com.mygaienko.reactive.repo.CarRepo;
import com.mygaienko.reactive.repo.entity.Car;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.UUID;

@Slf4j
@Service
@AllArgsConstructor
public class CarService {

    private final CarRepo carRepo;

    public Mono<Car> findByBrandAndModel(String brand, String model) {
        return carRepo.findByBrandAndModel(brand, model);
    }

    public Mono<Car> save(Car car) {
        String id = UUID.randomUUID().toString();
        log.info("Created new car with id - {}", id);
        return carRepo.save(car.toBuilder()
                .id(id)
                .build());
    }

    public Mono<Car> findById(String id) {
        return carRepo.findById(id);
    }

    public Flux<Car> findAll() {
        return carRepo.findAll();
    }

}
