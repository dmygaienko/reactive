package com.mygaienko.reactive.service;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import reactor.core.publisher.Flux;
import reactor.core.scheduler.Schedulers;

@Slf4j
class CarServiceTest {

    @Test
    void testJust() {
        Flux.just(1, 2, 3)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake() {
        Flux.just(1, 2, 3)
                .take(1)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake_subscribeOnParallelScheduler() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .subscribeOn(Schedulers.newParallel("subscribe-parallel-scheduler", 4))
                .take(3)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake_publishOnParallelScheduler() {
        Flux.just(1, 2, 3, 4, 5, 6)
                .log()
                .publishOn(Schedulers.newParallel("publish-parallel-scheduler", 4))
                .take(3)
                .subscribe(value -> log.info("Received value: {}", value));
    }

    @Test
    void testJustWithTake_publishOnParallelScheduler_subscribeOnParallelScheduler() {
        Flux.just(1, 2, 3, 4, 5, 6)
//                .log()
                .subscribeOn(Schedulers.newParallel("subscribe-parallel-scheduler", 4))
                .log()
                .take(3)
                .publishOn(Schedulers.newParallel("publish-parallel-scheduler", 4))
                .subscribe(value -> log.info("Received value: {}", value));
    }

}