package com.mygaienko.reactive.repo;

import com.mygaienko.reactive.repo.entity.Car;
import org.springframework.data.aerospike.repository.AerospikeRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarRepo extends AerospikeRepository<Car, Long> {

    Car findByBrandAndModel(String brand, String model);

}
