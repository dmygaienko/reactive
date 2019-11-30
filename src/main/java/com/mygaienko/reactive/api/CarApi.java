package com.mygaienko.reactive.api;

import com.mygaienko.reactive.api.dto.CarDto;
import com.mygaienko.reactive.service.CarService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cars")
@AllArgsConstructor
public class CarApi {

    private final CarService carService;

    @GetMapping
    public Flux<CarDto> findAll() {
        return carService.findAll().map(CarDto::toDto);
    }

    @GetMapping("/byParams")
    public Mono<CarDto> findByBrandAndModel(@RequestParam String brand, @RequestParam String model) {
        return carService.findByBrandAndModel(brand, model).map(CarDto::toDto);
    }

    @GetMapping("/{id}")
    public Mono<CarDto> findById(@PathVariable String id) {
        return carService.findById(id).map(CarDto::toDto);
    }

    @PostMapping
    public Mono<CarDto> save(@RequestBody CarDto carDto) {
        return carService.save(carDto.toEntity())
                .map(CarDto::toDto);
    }

    @GetMapping("/test")
    public Mono<CarDto> getTestDto() {
        return Mono.just(getTestCarDto());
    }

    private CarDto getTestCarDto() {
        return CarDto.builder()
                .brand("Mazda")
                .model("CX-5")
                .build();
    }

}
