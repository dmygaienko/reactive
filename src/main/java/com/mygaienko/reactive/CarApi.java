package com.mygaienko.reactive;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("/cars")
public class CarApi {

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
