package com.mygaienko.reactive.repo;

import com.mygaienko.reactive.repo.entity.Car;
import org.springframework.data.aerospike.repository.ReactiveAerospikeRepository;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Mono;

@Repository
public interface CarRepo extends ReactiveAerospikeRepository<Car, String> {

    Mono<Car> findByBrandAndModel(String brand, String model);

}
