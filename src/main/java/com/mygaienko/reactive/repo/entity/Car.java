package com.mygaienko.reactive.repo.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.aerospike.mapping.Document;
import org.springframework.data.annotation.Id;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document
@Builder(toBuilder = true)
public class Car {

    @Id
    String id;

    String brand;

    String model;

}
