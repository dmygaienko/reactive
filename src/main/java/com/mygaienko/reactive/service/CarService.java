package com.mygaienko.reactive.service;

import com.mygaienko.reactive.repo.CarRepo;
import com.mygaienko.reactive.repo.entity.Car;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@Service
@AllArgsConstructor
public class CarService {

    private final CarRepo carRepo;

    public Mono<Car> findByBrandAndModel(String brand, String model) {
        return Mono.just(carRepo.findByBrandAndModel(brand, model));
    }

    public void save(Car car) {
        carRepo.save(car);
    }

}
